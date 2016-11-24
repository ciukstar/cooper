package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Order;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sergiu
 */
@Named
@Stateless
public class OrderRepo extends AbstractRepo<Order> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Order>> e;

    @Override
    public List<Order> findAll() {
        final List<Order> res = super.findAll();
        e.fire(res);
        return res;
    }

    public OrderRepo() {
        super(Order.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
