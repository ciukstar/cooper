package edu.ciukstar.cooper.application.purchase;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Dispenser;
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
public class DispenserCache extends CrudCache<Dispenser> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Dispenser> op;
    private Dispenser entity;

    void refresh(@Observes List<Dispenser> source) {
        entity = refresher.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Dispenser> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Dispenser> getCrudOperation() {
        return op;
    }

    @Override
    public void setEntity(Dispenser entity) {
        this.entity = entity;
    }

    @Override
    public Dispenser getEntity() {
        return entity;
    }

}
