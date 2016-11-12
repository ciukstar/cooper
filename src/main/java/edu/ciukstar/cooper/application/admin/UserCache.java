package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.domain.User;
import edu.ciukstar.cooper.repo.UserRepo;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;

/**
 *
 * @author sergiu
 */
@Named
@FlowScoped("users")
public class UserCache implements Serializable {

    public void createUser() {
        repo.create(user);
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Inject
    private UserRepo repo;
    
    private User user;
    
}
