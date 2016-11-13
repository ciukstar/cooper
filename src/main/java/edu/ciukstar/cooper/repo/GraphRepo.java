package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.StatusGraph;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sergiu
 */
@Stateless
public class GraphRepo extends AbstractRepo<StatusGraph> {

    @PersistenceContext(unitName = "cooper")
    private EntityManager em;

    @Override
    public List<StatusGraph> findAll() {
        return super.findAll();
    }
    
    public GraphRepo() {
        super(StatusGraph.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
