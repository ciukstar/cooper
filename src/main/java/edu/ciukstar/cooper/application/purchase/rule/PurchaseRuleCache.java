package edu.ciukstar.cooper.application.purchase.rule;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.PurchaseRule;
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
public class PurchaseRuleCache extends CrudCache<PurchaseRule> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<PurchaseRule> op;
    private PurchaseRule entity;

    void refresh(@Observes List<PurchaseRule> source) {
        this.entity = refresher.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<PurchaseRule> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<PurchaseRule> getCrudOperation() {
        return op;
    }

    @Override
    public void setEntity(PurchaseRule entity) {
        this.entity = entity;
    }

    @Override
    public PurchaseRule getEntity() {
        return entity;
    }

}
