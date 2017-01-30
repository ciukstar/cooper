package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Category;
import java.util.List;
import java.util.stream.Collectors;
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
public class CategoryRepo extends AbstractRepo<Category> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Category>> e;

    public List<Category> findRoots() {
        return findAll().stream().filter(c -> c.hasSubcategories()).collect(Collectors.toList());
    }
    
    @Override
    public List<Category> findAll() {
        final List<Category> res = super.findAll();
        e.fire(res);
        return res;
    }

    public CategoryRepo() {
        super(Category.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
