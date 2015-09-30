package fi.hut.soberit.agilefant.transfer;

import java.util.ArrayList;
import java.util.List;
import fi.hut.soberit.agilefant.model.Iteration;
import fi.hut.soberit.agilefant.model.Product;
import fi.hut.soberit.agilefant.model.Team;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.security.SecurityUtil;
import fi.hut.soberit.agilefant.util.BeanCopier;

public class ProductTO extends Product implements LeafStoryContainer {

    private List<StoryTO> leafStories = new ArrayList<StoryTO>();
    private List<ProjectTO> childProjects = new ArrayList<ProjectTO>();
    
    private List<IterationTO> standaloneIterations = new ArrayList<IterationTO>();
    
    public ProductTO() {};
    public ProductTO(Product product) {
        BeanCopier.copy(product, this);
    }
    public List<StoryTO> getLeafStories() {
        return leafStories;
    }
    public void setLeafStories(List<StoryTO> leafStories) {
        this.leafStories = leafStories;
    }
    public List<ProjectTO> getChildProjects() {
        return childProjects;
    }
    public void setChildProjects(List<ProjectTO> childProjects) {
        this.childProjects = childProjects;
    }
    public List<IterationTO> getStandaloneIterations() {
        return standaloneIterations;
    }
    public List<IterationTO> getMyStandaloneIterations() {
        User loggedUser = getLoggedInUser();
        if (loggedUser.isAdmin()) {
            return standaloneIterations;
        }
        List<IterationTO> myStandaloneIterations = new ArrayList<IterationTO>();
        for (IterationTO iteration: standaloneIterations) {
            for (Team myTeam: loggedUser.getTeams()) {
                Boolean iterationAdded = false;
                for (Iteration teamIteration: myTeam.getIterations()) {
                    if (teamIteration.getId() == iteration.getId()) {
                        myStandaloneIterations.add(iteration);
                        iterationAdded = true;
                        break;
                    }
                }                
                if (iterationAdded) {
                    break;
                }
            }
        }
        return myStandaloneIterations;
    }
    private User getLoggedInUser() {
        return SecurityUtil.getLoggedUser();
    }
    public void setStandaloneIterations(List<IterationTO> standaloneIterations) {
        this.standaloneIterations = standaloneIterations;
    }
}
