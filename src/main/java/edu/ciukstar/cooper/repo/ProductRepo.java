package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Product;
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
public class ProductRepo extends AbstractRepo<Product> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Product>> e;

    @Override
    public List<Product> findAll() {
        final List<Product> res = super.findAll();
        e.fire(res);
        return res;
    }

    public ProductRepo() {
        super(Product.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
