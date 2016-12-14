package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Dispenser;
import edu.ciukstar.cooper.domain.Dispenser_;
import edu.ciukstar.cooper.domain.Purchase;
import java.util.Collections;
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
public class DispenserRepo extends AbstractRepo<Dispenser> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Dispenser>> e;

    public DispenserRepo() {
        super(Dispenser.class);
    }

    public List<Dispenser> findByPurchase(Purchase purchase) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Dispenser> cq = cb.createQuery(Dispenser.class);
        Root<Dispenser> dispenser = cq.from(Dispenser.class);
        cq.select(dispenser).where(cb.equal(dispenser.get(Dispenser_.purchase), purchase));
        final List<Dispenser> res = getEntityManager().createQuery(cq).getResultList();
        e.fire(res);
        return res;
    }

    @Override
    public List<Dispenser> findAll() {
        final List<Dispenser> res = super.findAll();
        e.fire(res);
        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}
