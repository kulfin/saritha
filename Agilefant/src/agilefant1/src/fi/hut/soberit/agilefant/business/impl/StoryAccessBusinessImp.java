package fi.hut.soberit.agilefant.business.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.hut.soberit.agilefant.business.StoryAccessBusiness;
import fi.hut.soberit.agilefant.business.StoryBusiness;
import fi.hut.soberit.agilefant.business.UserBusiness;
import fi.hut.soberit.agilefant.db.StoryAccessDAO;
import fi.hut.soberit.agilefant.db.history.StoryHistoryDAO;
import fi.hut.soberit.agilefant.model.Story;
import fi.hut.soberit.agilefant.model.StoryAccess;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.security.SecurityUtil;
import fi.hut.soberit.agilefant.transfer.StoryAccessCloudTO;

@Service("storyAccessBusiness")
@Transactional
public class StoryAccessBusinessImp extends GenericBusinessImpl<StoryAccess>
        implements StoryAccessBusiness {

    @Autowired
    private StoryAccessDAO storyAccessDAO;
    @Autowired
    private StoryBusiness storyBusiness;
    @Autowired
    private UserBusiness userBusiness;
    @Autowired
    private StoryHistoryDAO storyHistoryDAO;

    public StoryAccessBusinessImp() {
        super(StoryAccess.class);
    }

    public void addAccessEntry(Story story) {
        DateTime now = new DateTime();
        User user = SecurityUtil.getLoggedUser();

        StoryAccess entry = new StoryAccess();

        entry.setDate(now);
        entry.setStory(story);
        entry.setUser(user);
        this.storyAccessDAO.create(entry);
    }

    public void addAccessEntry(int storyId) {
        Story story = this.storyBusiness.retrieve(storyId);
        this.addAccessEntry(story);
    }

    @Transactional(readOnly=true)
    public List<StoryAccessCloudTO> calculateOccurences(DateTime start,
            DateTime end, int userId, int numberOfItems) {
        User user = this.userBusiness.retrieve(userId);
        return this.calculateOccurences(start, end, user, numberOfItems);

    }

    @Transactional(readOnly=true)
    public List<StoryAccessCloudTO> calculateEditOccurences(DateTime start,
            DateTime end, int userId, int numberOfItems) {
        User user = this.userBusiness.retrieve(userId);
        return this.calculateEditOccurences(start, end, user, numberOfItems);

    }
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<StoryAccessCloudTO> calculateOccurences(DateTime start,
            DateTime end, User user, int numberOfItems) {
        Map<Story, Long> data = this.storyAccessDAO.calculateAccessCounts(
                start, end, user);
        List<StoryAccessCloudTO> res = new ArrayList<StoryAccessCloudTO>();
        for (Story story : data.keySet()) {
            res.add(new StoryAccessCloudTO(story, data.get(story)));
        }
        Collections.sort(res, new PropertyComparator("count", true, false));
        if(res.size() > numberOfItems) {
            return res.subList(0, numberOfItems - 1);
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<StoryAccessCloudTO> calculateEditOccurences(DateTime start,
            DateTime end, User user, int numberOfItems) {
        Map<Integer, Long> data = this.storyHistoryDAO.calculateAccessCounts(
                start, end, user);
        
        Collection<Story> stories = this.storyBusiness.retrieveMultiple(data.keySet());
        
        List<StoryAccessCloudTO> res = new ArrayList<StoryAccessCloudTO>();
        for (Story story : stories) {
            res.add(new StoryAccessCloudTO(story, data.get(story.getId())));
        }
        Collections.sort(res, new PropertyComparator("count", true, false));
        if(res.size() > numberOfItems) {
            return res.subList(0, numberOfItems - 1);
        }
        return res;
    }

}
