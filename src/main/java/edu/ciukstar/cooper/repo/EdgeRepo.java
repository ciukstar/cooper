package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Graph;
import edu.ciukstar.cooper.domain.Edge;
import edu.ciukstar.cooper.domain.Edge_;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
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
public class EdgeRepo extends AbstractRepo<Edge> {

    @PersistenceContext(unitName = "cooper")
    private EntityManager em;
    @Inject
    private Event<Set<Edge>> event;

    public EdgeRepo() {
        super(Edge.class);
    }

    public Set<Edge> findByGraph(Graph graph) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Edge> cq = cb.createQuery(Edge.class);
        Root<Edge> vertex = cq.from(Edge.class);
        cq.select(vertex).where(cb.equal(vertex.get(Edge_.graph), graph));
        final Set<Edge> result = getEntityManager().createQuery(cq).getResultList().stream().collect(Collectors.toSet());
        event.fire(result);
        return result;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
