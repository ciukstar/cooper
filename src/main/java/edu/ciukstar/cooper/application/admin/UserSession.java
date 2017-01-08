package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.domain.User;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private LocalDateTime start;

    public UserSession setupUserSession(User user) {
        this.user = user;
        this.start = LocalDateTime.now();
        return this;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public User getUser() {
        return user;
    }
    
}
