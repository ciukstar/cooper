package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Photo;
import edu.ciukstar.cooper.domain.Photo_;
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
public class PhotoRepo extends AbstractRepo<Photo> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Photo>> e;

    public Photo getByReservedName(String reservedName) {
        return findByReservedName(reservedName).orElse(null);
    }
    
    public Optional<Photo> findByReservedName(String reservedName) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Photo> cq = cb.createQuery(Photo.class);
        Root<Photo> photo = cq.from(Photo.class);
        cq.select(photo).where(cb.equal(photo.get(Photo_.reservedName), reservedName));
        
        try {
            return Optional.of(getEntityManager().createQuery(cq).getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
    
    @Override
    public List<Photo> findAll() {
        final List<Photo> res = super.findAll();
        e.fire(res);
        return res;
    }

    public PhotoRepo() {
        super(Photo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
