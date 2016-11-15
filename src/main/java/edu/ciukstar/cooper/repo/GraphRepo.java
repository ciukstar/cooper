package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Graph;
import java.util.List;
import javax.ejb.Stateless;
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

    @Override
    public List<Graph> findAll() {
        return super.findAll();
    }
    
    public GraphRepo() {
        super(Graph.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
