package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Purchase;
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
public class PurchaseRepo extends AbstractRepo<Purchase> {

    @PersistenceContext(unitName = "cooper")
    private EntityManager em;
    @Inject
    private Event<List<Purchase>> e;

    @Override
    public List<Purchase> findAll() {
        final List<Purchase> res = super.findAll();
        e.fire(res);
        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseRepo() {
        super(Purchase.class);
    }

}
