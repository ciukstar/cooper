package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Graph;
import edu.ciukstar.cooper.domain.Graph_;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class GraphRepo extends AbstractRepo<Graph> {

    @PersistenceContext(unitName = "cooper")
    private EntityManager em;
    @Inject
    private Event<List<Graph>> event;

    public Optional<Graph> findByCode(String code) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Graph> cq = cb.createQuery(Graph.class);
        Root<Graph> graph = cq.from(Graph.class);
        cq.select(graph).where(cb.equal(graph.get(Graph_.code), code));
        try {
            return Optional.of(getEntityManager().createQuery(cq).getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

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
