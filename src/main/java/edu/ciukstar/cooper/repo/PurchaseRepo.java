package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Purchase;
import edu.ciukstar.cooper.domain.Purchase_;
import edu.ciukstar.cooper.domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

    public List<Purchase> findByOrganizer(User organizer) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Purchase> cq = cb.createQuery(Purchase.class);
        Root<Purchase> purchase = cq.from(Purchase.class);
        cq.select(purchase).where(cb.equal(purchase.get(Purchase_.organizer), organizer));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
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
