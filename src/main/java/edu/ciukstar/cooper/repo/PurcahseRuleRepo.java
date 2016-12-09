package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.PurchaseRule;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sergiu
 */
@Stateless
public class PurcahseRuleRepo extends AbstractRepo<PurchaseRule> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<PurchaseRule>> e;

    public PurcahseRuleRepo() {
        super(PurchaseRule.class);
    }

    @Override
    public List<PurchaseRule> findAll() {
        final List<PurchaseRule> res = super.findAll();
        e.fire(res);
        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}
