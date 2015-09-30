package fi.hut.soberit.agilefant.web.widgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fi.hut.soberit.agilefant.business.AuthorizationBusiness;
import fi.hut.soberit.agilefant.business.StoryBusiness;
import fi.hut.soberit.agilefant.model.Story;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.security.SecurityUtil;
import fi.hut.soberit.agilefant.util.StoryMetrics;

@Component("storyWidget")
@Scope("prototype")
public class StoryWidget extends CommonWidget {

    private static final long serialVersionUID = 7810437122662724707L;

    private Story story;
    private StoryMetrics storyMetrics;
    
    @Autowired
    private StoryBusiness storyBusiness;
    
    @Autowired
    private AuthorizationBusiness authorizationBusiness;
    
    @Override
    public String execute() {
        story = storyBusiness.retrieve(getObjectId());
        storyMetrics = storyBusiness.calculateMetrics(story);
        return SUCCESS;
    }
    /*
    public boolean getAccess() {
        User user = SecurityUtil.getLoggedUser();
        //int backlogId = story.getIteration() != null ? story.getIteration().getId() : story.getBacklog().getId();
        if (story.getIteration() != null) {
            boolean access = this.authorizationBusiness.isBacklogAccessible(story.getIteration().getId(), user);
            if (access || story.getBacklog() == null) {
                return access;
            }
        }
        return this.authorizationBusiness.isBacklogAccessible(story.getBacklog().getId(), user);
    }
    */
    
    public boolean getAccess() {
        User user = SecurityUtil.getLoggedUser();
        if (story.getIteration() != null) {
            boolean access = this.authorizationBusiness.isBacklogAccessible(story.getIteration().getId(), user);
            if (access || story.getBacklog() == null) {
                return access;
            }
        }
        return this.authorizationBusiness.isBacklogAccessible(story.getBacklog().getId(), user);
    }

    public Story getStory() {
        return story;
    }
    
    public StoryMetrics getStoryMetrics() {
        return storyMetrics;
    }
}
