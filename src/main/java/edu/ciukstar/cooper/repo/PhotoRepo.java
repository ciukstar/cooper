package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Photo;
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
public class PhotoRepo extends AbstractRepo<Photo> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Photo>> e;

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
