package fi.hut.soberit.agilefant.web;


import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import fi.hut.soberit.agilefant.business.BacklogBusiness;
import fi.hut.soberit.agilefant.business.LoginBusiness;
import fi.hut.soberit.agilefant.business.SettingBusiness;
import fi.hut.soberit.agilefant.business.UserBusiness;
import fi.hut.soberit.agilefant.model.Login;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.security.SecurityUtil;

@Component("loginContextAction")
@Scope("prototype")
public class LoginContextAction extends ActionSupport {

    private static final long serialVersionUID = -477483113446767662L;

    @Autowired
    private SettingBusiness settingBusiness;
    
    @Autowired
    private BacklogBusiness backlogBusiness;
    
    @Autowired
    private UserBusiness userBusiness;
    
    @Autowired
    private LoginBusiness loginBusiness;
    

    @Override
    public String execute(){
        saveLoginInformation();
        
        if (loginBusiness.retrieveLoginCountByUser(getLoggedInUser()) < 2) {
            return "help";
        }
        else if (settingBusiness.isDailyWork()) {
            return "dailyWork";
        }
        else {
            return "selectBacklog";
        }
    }
    
    private void saveLoginInformation() {
        User loggedUser = getLoggedInUser();
        DateTime now = new DateTime();
        Login login = new Login();
        login.setUser(loggedUser);
        login.setTime(now);
        
        loginBusiness.store(login);
    }
    
    private User getLoggedInUser() {
        return SecurityUtil.getLoggedUser();
    }

}
