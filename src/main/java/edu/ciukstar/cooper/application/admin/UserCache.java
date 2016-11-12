package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.domain.User;
import edu.ciukstar.cooper.repo.CrudOperation;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class UserCache implements Serializable {

    private User user;
    private CrudOperation crudOperation;

    public void cancelCrud() {
        this.crudOperation = null;
        this.user = null;
    }

    public void performCrud() {
        crudOperation.execute();
    }

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
