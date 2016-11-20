package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Resource;
import edu.ciukstar.cooper.domain.Resource_;
import edu.ciukstar.cooper.domain.Role;
import java.util.List;
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
public class ResourceRepo extends AbstractRepo<Resource> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Resource>> e;

    public ResourceRepo() {
        super(Resource.class);
    }

    public List<Resource> findByRole(Role role) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Resource> cq = cb.createQuery(Resource.class);
        Root<Resource> resource = cq.from(Resource.class);
        cq.select(resource).where(cb.equal(resource.get(Resource_.role), role));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
