package edu.ciukstar.cooper.application.product;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Warehouse;
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
public class WarehouseCache extends CrudCache<Warehouse> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Warehouse> op;
    private Warehouse entity;

    void refresh(@Observes List<Warehouse> source) {
        this.entity = refresher.match(entity, source).orElse(null);
    }
    
    @Override
    protected void setCrudOperation(CrudOperation<Warehouse> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Warehouse> getCrudOperation() {
        return this.op;
    }

    @Override
    public void setEntity(Warehouse entity) {
        this.entity = entity;
    }

    @Override
    public Warehouse getEntity() {
        return entity;
    }

    
}
