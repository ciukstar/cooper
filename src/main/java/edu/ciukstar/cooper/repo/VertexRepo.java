package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Graph;
import edu.ciukstar.cooper.domain.Vertex;
import edu.ciukstar.cooper.domain.Vertex_;
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
public class VertexRepo extends AbstractRepo<Vertex> {

    @PersistenceContext(unitName = "cooper")
    private EntityManager em;
    @Inject
    private Event<Set<Vertex>> event;

    public VertexRepo() {
        super(Vertex.class);
    }

    public Set<Vertex> findByGraph(Graph graph) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Vertex> cq = cb.createQuery(Vertex.class);
        Root<Vertex> vertex = cq.from(Vertex.class);
        cq.select(vertex).where(cb.equal(vertex.get(Vertex_.graph), graph));
        final Set<Vertex> result = getEntityManager().createQuery(cq).getResultList().stream().collect(Collectors.toSet());
        event.fire(result);
        return result;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
