package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.domain.User;
import edu.ciukstar.cooper.repo.CrudOperation;
import edu.ciukstar.cooper.repo.UserRepo;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class UserCache implements Serializable {

    @Inject
    private UserRepo repo;    
    private User user;
    private CrudOperation crudOperation;

    public void schedule(CrudOperation<User> crud) {
        this.crudOperation = crud;
        this.user = crud.getEntity();
    }

    public User createUser() {
        return user = new User();
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}
