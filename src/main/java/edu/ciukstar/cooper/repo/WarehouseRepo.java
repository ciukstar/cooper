package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Warehouse;
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
public class WarehouseRepo extends AbstractRepo<Warehouse> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Warehouse>> e;

    @Override
    public List<Warehouse> findAll() {
        final List<Warehouse> res = super.findAll();
        e.fire(res);
        return res;
    }

    public WarehouseRepo() {
        super(Warehouse.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
