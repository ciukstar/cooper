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
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class UserCache extends CrudCache<User> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation op;
    private User entity;

    public void uploadUserPhoto(FileUploadEvent e) {
        entity.setPhoto(e.getFile().getContents());
    }
    
    void refresh(@Observes List<User> source) {
        entity = refresher.match(entity, source).orElse(null);
    }
    
    @Override
    protected void setCrudOperation(CrudOperation<User> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<User> getCrudOperation() {
        return this.op;
    }

    @Override
    public void setEntity(User entity) {
        this.entity = entity;
    }

    @Override
    public User getEntity() {
        return entity;
    }

}
