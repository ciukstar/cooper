package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Country;
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
public class CountryRepo extends AbstractRepo<Country> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Country>> e;
    
    public CountryRepo() {
        super(Country.class);
    }

    @Override
    public List<Country> findAll() {
        final List<Country> res = super.findAll();
        e.fire(res);
        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
