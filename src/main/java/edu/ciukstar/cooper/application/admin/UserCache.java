package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.application.CrudCache;
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
public class UserCache extends CrudCache<User> implements Serializable {

    private User user;
    private CrudOperation crudOperation;

    @Override
    protected void setCrudOperation(CrudOperation<User> op) {
        this.crudOperation = op;
    }

    @Override
    protected CrudOperation<User> getCrudOperation() {
        return this.crudOperation;
    }

    @Override
    protected void setEntity(User entity) {
        this.user = entity;
    }

    @Override
    public User getEntity() {
        return user;
    }

}
