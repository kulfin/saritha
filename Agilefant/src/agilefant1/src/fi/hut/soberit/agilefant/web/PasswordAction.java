package fi.hut.soberit.agilefant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fi.hut.soberit.agilefant.business.PasswordBusiness;
import fi.hut.soberit.agilefant.business.UserBusiness;
import fi.hut.soberit.agilefant.model.User;

/**
 * Action for generating & mailing new passwords to <code>Users</code> who
 * have lost theirs.
 * 
 * @author Teemu Ilmonen
 * 
 */
@Component("passwordAction")
@Scope("prototype")
public class PasswordAction extends ActionSupport {
    private static final long serialVersionUID = -5808987058415748396L;

    @Autowired
    private PasswordBusiness passwordBusiness;

    @Autowired
    private UserBusiness userBusiness;

    private String name;

    private String email;

    /**
     * Generates a new password for an <code>User</code>
     * 
     * @return
     */
    public String generate() {
        User user = userBusiness.retrieveByLoginName(name);
        if (user == null) {
            return Action.ERROR; // User not found.
        }
        else {
            String user_email = user.getEmail();
            if (user_email == null || !user_email.equalsIgnoreCase(email)) {
                return Action.ERROR;
            }
        }

        passwordBusiness.generateAndMailPassword(user.getId());
        addActionMessage("A new password has been sent to: " + user.getEmail()
                + ".");
        return Action.SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordBusiness(PasswordBusiness passwordBusiness) {
        this.passwordBusiness = passwordBusiness;
    }

    public void setUserBusiness(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

}
