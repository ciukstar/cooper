package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Currency;
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
public class CurrencyRepo extends AbstractRepo<Currency> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Currency>> e;

    public CurrencyRepo() {
        super(Currency.class);
    }

    @Override
    public List<Currency> findAll() {
        final List<Currency> res = super.findAll();
        e.fire(res);
        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
