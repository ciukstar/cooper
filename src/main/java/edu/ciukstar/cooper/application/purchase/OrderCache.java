package edu.ciukstar.cooper.application.purchase;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Order;
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
public class OrderCache extends CrudCache<Order> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Order> op;
    private Order entity;

    void refresh(@Observes List<Order> source) {
        this.entity = refresher.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Order> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Order> getCrudOperation() {
        return this.op;
    }

    @Override
    public void setEntity(Order entity) {
        this.entity = entity;
    }

    @Override
    public Order getEntity() {
        return this.entity;
    }

}
