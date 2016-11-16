package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Graph;
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
public class GraphRepo extends AbstractRepo<Graph> {

    @PersistenceContext(unitName = "cooper")
    private EntityManager em;
    @Inject
    private Event<List<Graph>> event;

    @Override
    public List<Graph> findAll() {
        final List<Graph> res = super.findAll();
        event.fire(res);
        return res;
    }

    public GraphRepo() {
        super(Graph.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
