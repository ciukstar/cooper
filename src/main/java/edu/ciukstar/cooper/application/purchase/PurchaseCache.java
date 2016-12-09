package edu.ciukstar.cooper.application.purchase;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Purchase;
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
public class PurchaseCache extends CrudCache<Purchase> implements Serializable {

    @Inject
    private Refresher r;
    private CrudOperation<Purchase> op;
    private Purchase entity;

    void refresh(@Observes List<Purchase> source) {
        entity = r.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Purchase> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Purchase> getCrudOperation() {
        return this.op;
    }

    @Override
    public void setEntity(Purchase entity) {
        this.entity = entity;
    }

    @Override
    public Purchase getEntity() {
        return this.entity;
    }

}
