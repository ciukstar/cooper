package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.User;
import edu.ciukstar.cooper.repo.CrudOperation;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class UserCache extends CrudCache<User> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation crudOperation;
    private User user;

    void refresh(@Observes List<User> source) {
        user = refresher.match(user, source).orElse(null);
    }
    
    @Override
    protected void setCrudOperation(CrudOperation<User> op) {
        this.crudOperation = op;
    }

    @Override
    protected CrudOperation<User> getCrudOperation() {
        return this.crudOperation;
    }

    @Override
    public void setEntity(User entity) {
        this.user = entity;
    }

    @Override
    public User getEntity() {
        return user;
    }

}
