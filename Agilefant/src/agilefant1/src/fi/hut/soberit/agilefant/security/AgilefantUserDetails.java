package fi.hut.soberit.agilefant.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.ImmutableList;

import fi.hut.soberit.agilefant.model.User;

/**
 * Spring Security UserDetails-implementation.
 * <p>
 * This is the "glue" between our system and spring security authentication. Basically, we
 * provide these on request trough AgilefantUserDetailsService.
 * <p>
 * TODO: Should the user object be re-requested every time when "getUser" is
 * called due to Hibernate session issues? If so, how do we obtain UserDAO here?
 * 
 * @see AgilefantUserDetailsService
 * @author Turkka Äijälä
 */
public class AgilefantUserDetails implements UserDetails {

    private static final long serialVersionUID = 1262586472763367026L;

    private String username;

    private String password;
    
    private boolean enabled;

    private int userId;
    
    private boolean admin;

    public AgilefantUserDetails(User user) {
        username = user.getLoginName();
        password = user.getPassword();
        userId = user.getId();
        enabled = user.isEnabled();
        admin = user.isAdmin();
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // I have no idea what's the proper thing to put here
        if(admin){
            return ImmutableList.of(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN"));
        } else {
            return ImmutableList.of(new SimpleGrantedAuthority("USER"));
        }
    }

    /**
     * Provide password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Provide username.
     */
    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return enabled;
    }
    
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Extra functionality to provide userId for callers.
     */
    public int getUserId() {
        return userId;
    }
}
