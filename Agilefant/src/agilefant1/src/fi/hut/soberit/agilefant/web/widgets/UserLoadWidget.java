package fi.hut.soberit.agilefant.web.widgets;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fi.hut.soberit.agilefant.business.AuthorizationBusiness;
import fi.hut.soberit.agilefant.business.UserBusiness;
import fi.hut.soberit.agilefant.model.Team;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.security.SecurityUtil;

@Component("userLoadWidget")
@Scope("prototype")
public class UserLoadWidget extends CommonWidget {

    private static final long serialVersionUID = 7810437122662724707L;

    private User user;
    
    @Autowired
    private UserBusiness userBusiness;
    
    @Autowired
    private AuthorizationBusiness authorizationBusiness;
    
    @Override
    public String execute() {
        user = userBusiness.retrieve(getObjectId());
        return SUCCESS;
    }
    
    public boolean getAccess() {
        User currentUser = SecurityUtil.getLoggedUser();
        return this.authorizationBusiness.isUserAccessible(user.getId(), currentUser);
    }

    public User getUser() {
        return user;
    }
}
