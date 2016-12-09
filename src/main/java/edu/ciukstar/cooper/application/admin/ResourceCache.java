package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Resource;
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
public class ResourceCache extends CrudCache<Resource> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Resource> op;
    private Resource entity;

    void refresh(@Observes List<Resource> source) {
        this.entity = refresher.match(entity, source).orElse(null);
    }
    
    @Override
    protected void setCrudOperation(CrudOperation<Resource> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Resource> getCrudOperation() {
        return this.op;
    }

    @Override
    public void setEntity(Resource entity) {
        this.entity = entity;
    }

    @Override
    public Resource getEntity() {
        return entity;
    }

}
