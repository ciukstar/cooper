package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Manufacturer;
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
public class ManufacturerRepo extends AbstractRepo<Manufacturer> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Manufacturer>> e;

    public ManufacturerRepo() {
        super(Manufacturer.class);
    }

    @Override
    public List<Manufacturer> findAll() {
        final List<Manufacturer> res = super.findAll();
        e.fire(res);
        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
