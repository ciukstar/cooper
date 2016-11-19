package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Role;
import edu.ciukstar.cooper.repo.CrudOperation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class RoleCache extends CrudCache<Role> implements Serializable {
    @Inject
    private Refresher refresher;
    private CrudOperation<Role> op;
    private Role entity;

    void refresh(@Observes List<Role> source) {
        this.entity = refresher.match(entity, source).orElse(null);
    }
    
    @Override
    protected void setCrudOperation(CrudOperation<Role> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Role> getCrudOperation() {
        return this.op;
    }

    @Override
    public void setEntity(Role entity) {
        this.entity = entity;
    }

    @Override
    public Role getEntity() {
        return this.entity;
    }

}
