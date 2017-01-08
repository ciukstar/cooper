package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.domain.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class UserSession implements Serializable {

    private User user;

    public UserSession setupUserSession(User user) {
        this.user = user;
        return this;
    }
    
}
