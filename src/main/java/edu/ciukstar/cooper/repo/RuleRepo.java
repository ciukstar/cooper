package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Rule;
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
public class RuleRepo extends AbstractRepo<Rule> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Rule>> e;

    public RuleRepo() {
        super(Rule.class);
    }

    @Override
    public List<Rule> findAll() {
        final List<Rule> res = super.findAll();
        e.fire(res);
        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}
