package fi.hut.soberit.agilefant.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.hut.soberit.agilefant.business.AuthorizationBusiness;
import fi.hut.soberit.agilefant.business.SearchBusiness;
import fi.hut.soberit.agilefant.db.BacklogDAO;
import fi.hut.soberit.agilefant.db.StoryDAO;
import fi.hut.soberit.agilefant.db.TaskDAO;
import fi.hut.soberit.agilefant.db.UserDAO;
import fi.hut.soberit.agilefant.model.Backlog;
import fi.hut.soberit.agilefant.model.Iteration;
import fi.hut.soberit.agilefant.model.NamedObject;
import fi.hut.soberit.agilefant.model.Product;
import fi.hut.soberit.agilefant.model.Project;
import fi.hut.soberit.agilefant.model.Story;
import fi.hut.soberit.agilefant.model.Task;
import fi.hut.soberit.agilefant.model.Team;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.security.SecurityUtil;
import fi.hut.soberit.agilefant.transfer.SearchResultRow;

@Service("searchBusiness")
public class SearchBusinessImpl implements SearchBusiness {

    @Autowired
    private StoryDAO storyDAO;
    @Autowired
    private BacklogDAO backlogDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private AuthorizationBusiness authorizationBusiness;
    
    @Transactional(readOnly=true)
    public List<SearchResultRow> searchStoriesAndBacklog(String searchTerm) {
        List<SearchResultRow> result = new ArrayList<SearchResultRow>();
        NamedObject quickRefMatch = this.searchByReference(searchTerm);
        if (quickRefMatch != null) {
            result.add(new SearchResultRow(quickRefMatch.getName(),
                    quickRefMatch));
        }
        List<Backlog> backlogs = backlogDAO.searchByName(searchTerm);
        List<Story> stories = storyDAO.searchByName(searchTerm);
        List<Task> tasks = taskDAO.searchByName(searchTerm);
        try {
            Integer searchTermId = Integer.parseInt(searchTerm);
            backlogs.addAll(backlogDAO.searchByID(searchTermId));
            stories.addAll(storyDAO.searchByID(searchTermId));
            tasks.addAll(taskDAO.searchByID(searchTermId));
        } catch (Exception e) {
            // The search term is not an integer
        }
        backlogListSearchResult(result, backlogs);
        storyListSearchResult(result, stories);
        taskListSearchResult(result, tasks);
        
        return result;
    }

    private void storyListSearchResult(List<SearchResultRow> result,
            List<Story> stories) {
        HashMap<Integer, Boolean> backlogAccessMap = new HashMap<Integer, Boolean>();
        for (Story story : stories) {
            Backlog backlog = story.getIteration();
            if(backlog == null) {
                backlog = story.getBacklog();
            }
            if(checkAccess(backlog, backlogAccessMap)){
                result.add(new SearchResultRow(backlog.getName() + " > "
                    + story.getName(), story));
            }
        }
    }
    
    private void taskListSearchResult(List<SearchResultRow> result,
            List<Task> tasks) {
        HashMap<Integer, Boolean> backlogAccessMap = new HashMap<Integer, Boolean>();
        for(Task task : tasks) {
            if(task.getStory()!= null){
                Backlog backlog = task.getStory().getBacklog();
                Iteration iteration = task.getStory().getIteration();
                if((backlog!=null && checkAccess(backlog, backlogAccessMap)) || (iteration!=null && checkAccess(iteration, backlogAccessMap))){
                    result.add(new SearchResultRow(task.getStory().getName() + " > " + 
                        task.getName(), task));
                }
            }
            if(task.getIteration() != null){
                if(checkAccess(task.getIteration(), backlogAccessMap)){  
                    result.add(new SearchResultRow(task.getIteration().getName() + " > " + 
                        task.getName(), task));
                }
            }
        }
    }

    private void backlogListSearchResult(List<SearchResultRow> result,
            List<Backlog> backlogs) {
        HashMap<Integer, Boolean> backlogAccessMap = new HashMap<Integer, Boolean>();
        for (Backlog bl : backlogs) {
            if(checkAccess(bl, backlogAccessMap)){            
                SearchResultRow item = new SearchResultRow();
                item.setOriginalObject(bl);
                if (bl.getParent() != null) {
                    item.setLabel(bl.getParent().getName() + " > " + bl.getName());
                } else {
                    item.setLabel(bl.getName());
                }
                result.add(item);
            }
        }
    }
    
    private boolean checkAccess(Backlog bl, HashMap<Integer, Boolean> backlogAccessMap){
        Boolean access = backlogAccessMap.get(bl.getId());
        if (access != null) {
            return access;
        }
        
        access = this.authorizationBusiness.isBacklogAccessible(bl.getId(), SecurityUtil.getLoggedUser());
        backlogAccessMap.put(bl.getId(), access);
        return access;
    }
    
    private boolean checkUserAccess(User user){
        Boolean access = this.authorizationBusiness.isUserAccessible(user.getId(), SecurityUtil.getLoggedUser());
        return access;
    }

    public NamedObject searchByReference(String searchTerm) {
        if (searchTerm == null) {
            return null;
        }

        String[] matches = searchTerm.split(":");
        int objectId;
        String type;
        if (matches.length != 2) {
            return null;
        }
        type = matches[0];

        try {
            objectId = Integer.parseInt(matches[1]);
        } catch (Exception e) {
            return null;
        }
        HashMap<Integer, Boolean> backlogAccessMap = new HashMap<Integer, Boolean>();
        if (type.equals("story")) {
            Story story = storyDAO.get(objectId);
            if(story != null && story.getBacklog() != null && checkAccess(story.getBacklog(), backlogAccessMap)){  
                return story;
            } else if (story != null && story.getBacklog() == null){
                return story.getIteration();
            }
        } else if (type.equals("task")) {
            Task task = taskDAO.get(objectId);
            if(task != null && task.getStory() != null) {
            	if(task.getStory().getBacklog() != null && checkAccess(task.getStory().getBacklog(), backlogAccessMap)){
            		if (task.getStory().hasChildren()) {
            			return task.getStory();
            		} else {
            			return task;
            		}
                } else if (task.getStory().getIteration() != null && checkAccess(task.getStory().getIteration(), backlogAccessMap)) {
                	return task;
                }
            } else if (task != null && task.getIteration() != null && checkAccess(task.getIteration(), backlogAccessMap)){
                return task;
            }
        } else if (type.equals("backlog")) {
            Backlog bl = backlogDAO.get(objectId);
            if(bl!=null && checkAccess(bl, backlogAccessMap)){  
                return bl;
            }
        }
        return null;
    }
    
    public List<SearchResultRow> searchIterations(String searchTerm) {
        List<SearchResultRow> result = new ArrayList<SearchResultRow>();
        List<Backlog> backlogs = backlogDAO.searchByName(searchTerm, Iteration.class);
        backlogListSearchResult(result, backlogs);
        return result;
    }

    public List<SearchResultRow> searchProjects(String searchTerm) {
        List<SearchResultRow> result = new ArrayList<SearchResultRow>();
        List<Backlog> backlogs = backlogDAO.searchByName(searchTerm, Project.class);
        backlogListSearchResult(result, backlogs);
        return result;
    }

    public List<SearchResultRow> searchStories(String searchTerm) {
        List<SearchResultRow> result = new ArrayList<SearchResultRow>();
        List<Story> stories = storyDAO.searchByName(searchTerm);
        storyListSearchResult(result, stories);
        return result;
    }

    public List<SearchResultRow> searchUsers(String searchTerm) {
        List<SearchResultRow> result = new ArrayList<SearchResultRow>();
        List<User> users = userDAO.searchByName(searchTerm);
        for(User user : users) {
            if (checkUserAccess(user)) {
                result.add(new SearchResultRow(user.getFullName(), user));
            }
        }
        return result;
    }
    
    public List<SearchResultRow> searchTasks(String searchTerm) {
        HashMap<Integer, Boolean> backlogAccessMap = new HashMap<Integer, Boolean>();
        List<SearchResultRow> result = new ArrayList<SearchResultRow>();
        List<Task> tasks = taskDAO.searchByName(searchTerm);
        for(Task task : tasks) {
            if(task.getStory() != null){
                if(checkAccess(task.getStory().getBacklog(), backlogAccessMap)){  
                    result.add(new SearchResultRow(task.getIteration().getName() + " > " + task.getStory().getName() + " > " + 
                        task.getName(), task));
                }
            } else {
                if(checkAccess(task.getIteration(), backlogAccessMap)){  
                    result.add(new SearchResultRow(task.getIteration().getName() + " > No Story > " + 
                        task.getName(), task));
                }
            }
        }
        return result;
    }
}
