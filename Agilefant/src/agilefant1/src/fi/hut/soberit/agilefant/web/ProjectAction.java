package fi.hut.soberit.agilefant.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;

import fi.hut.soberit.agilefant.annotations.PrefetchId;
import fi.hut.soberit.agilefant.business.IterationBusiness;
import fi.hut.soberit.agilefant.business.ProjectBusiness;
import fi.hut.soberit.agilefant.business.StoryBusiness;
import fi.hut.soberit.agilefant.model.ExactEstimate;
import fi.hut.soberit.agilefant.model.Iteration;
import fi.hut.soberit.agilefant.model.Project;
import fi.hut.soberit.agilefant.model.Story;
import fi.hut.soberit.agilefant.transfer.IterationMetrics;
import fi.hut.soberit.agilefant.transfer.IterationTO;
import fi.hut.soberit.agilefant.transfer.ProjectMetrics;
import fi.hut.soberit.agilefant.transfer.ProjectTO;
import fi.hut.soberit.agilefant.transfer.StoryTO;
import fi.hut.soberit.agilefant.util.StoryFilters;

@Component("projectAction")
@Scope("prototype")
public class ProjectAction implements CRUDAction, Prefetching, ContextAware {
    
    private static final long serialVersionUID = -4636900464606739866L;

    @PrefetchId
    private int projectId;
    
    private Integer productId = null;

    private Integer rankUnderId = null;
    
    private Integer rankOverId = null;

    private Project project;
    
    private ProjectMetrics projectMetrics;
    
    private long totalSpentEffort;
    
    private List<Story> stories;
    
    private List<StoryTO> leafStories;

    private Set<Integer> assigneeIds = new HashSet<Integer>();
    
    private boolean assigneesChanged = false;
    
    private String confirmationString;
    
    private List<IterationTO> iterations;
    
    @Autowired
    private ProjectBusiness projectBusiness;
    
    @Autowired 
    StoryBusiness storyBusiness;
    
    @Autowired 
    IterationBusiness iterationBusiness;
    
    public String iterationList() {
        iterations = projectBusiness.retrieveProjectIterations(projectId);
        for (IterationTO iteration : iterations) {
        	IterationMetrics iterationMetrics = iterationBusiness.getIterationMetrics(iteration);
        	iteration.setIterationMetrics(iterationMetrics);
        }
        return Action.SUCCESS;
    }
    
    public String rankProject() {
        project = projectBusiness.rankUnderProject(projectId, rankUnderId);
        return Action.SUCCESS;
    }
    
    public String rankOverProject() {
        project = projectBusiness.rankOverProject(projectId, rankOverId);
        return Action.SUCCESS;
    }
    
    public String unrankProject() {
        projectBusiness.unrankProject(projectId);
        return Action.SUCCESS;
    }
    
    public String projectMetrics() {
        project = projectBusiness.retrieve(projectId);
        projectMetrics = projectBusiness.getProjectMetrics(project);
        return Action.SUCCESS;
    }
    
    public String projectTotalSpentEffort() {
        project = projectBusiness.retrieve(projectId);
        this.totalSpentEffort = projectBusiness.getProjectTotalSpentEffort(project);
        return Action.SUCCESS;
    }
    
    public String projectData() {
        project = projectBusiness.getProjectData(projectId);
        return Action.SUCCESS;
    }
    
    public String create() {
        project = new Project();
        project.setStartDate(new DateTime());
        project.setEndDate(new DateTime());
        stories = new ArrayList<Story>();
        leafStories = new ArrayList<StoryTO>();
        iterations = new ArrayList<IterationTO>();
        return Action.SUCCESS;
    }

    public String retrieve() {
        project = this.projectBusiness.retrieve(projectId);
        stories = storyBusiness.retrieveStoriesInBacklog(project);
        leafStories = projectBusiness.retrieveLeafStories(projectId, new StoryFilters());
        iterations = projectBusiness.retrieveProjectIterations(projectId);
        return Action.SUCCESS;
    }
    
    public String store() {
       Set<Integer> assignees = null;
       if (assigneesChanged) {
           assignees = assigneeIds;
       }
       project = this.projectBusiness.store(projectId, productId, project, assignees);
       return Action.SUCCESS;
    }
    
    public String delete() {
        if(confirmationString.equalsIgnoreCase("yes")) {
            productId = projectBusiness.retrieve(projectId).getParent().getId();
            projectBusiness.delete(projectId);
            return Action.SUCCESS;
        } else {
            return Action.ERROR;
        }
    }

    public void initializePrefetchedData(int objectId) {
        this.project = this.projectBusiness.retrieve(objectId);        
    }
    
    public String moveToRanked() {
        this.projectBusiness.moveToRanked(projectId);
        return Action.SUCCESS;
    }
    
    public String getContextName() {
        return "backlog";
    }
    
    public int getContextObjectId() {
        return projectId;
    }
    
    
    //GETTERS AND SETTERS
    
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
	
    public void setProjectBusiness(ProjectBusiness projectBusiness) {
        this.projectBusiness = projectBusiness;
    }
    
    public void setStoryBusiness(StoryBusiness storyBusiness) {
        this.storyBusiness = storyBusiness;
    }

    public ProjectMetrics getProjectMetrics() {
        return projectMetrics;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public List<Story> getStories() {
        return stories;
    }
    
    public List<StoryTO> getLeafStories() {
        return leafStories;
    }
    
    public Integer getRankUnderId() {
        return rankUnderId;
    }
    public void setRankUnderId(Integer rankUnderId) {
        this.rankUnderId = rankUnderId;
    }
    public Set<Integer> getAssigneeIds() {
        return assigneeIds;
    }
    
    public void setAssigneeIds(Set<Integer> assigneeIds) {
        this.assigneeIds = assigneeIds;
    }
    
    public void setAssigneesChanged(boolean assigneesChanged) {
        this.assigneesChanged = assigneesChanged;
    }
    
    public boolean isAssigneesChanged() {
        return assigneesChanged;
    }
    
    public void setRankOverId(Integer rankOverId) {
        this.rankOverId = rankOverId;
    }
    public Integer getRankOverId() {
        return rankOverId;
    }
   
    public void setConfirmationString(String confirmationString) {
        this.confirmationString = confirmationString;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public List<IterationTO> getIterations() {
        return iterations;
    }

    public void setIterations(List<IterationTO> iterations) {
        this.iterations = iterations;
    }

	public long getTotalSpentEffort() {
		return totalSpentEffort;
	}

	public void setTotalSpentEffort(long totalSpentEffort) {
		this.totalSpentEffort = totalSpentEffort;
	}
    
}
