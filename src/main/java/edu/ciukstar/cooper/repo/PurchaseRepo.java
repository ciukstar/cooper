package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Purchase;
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
public class PurchaseRepo extends AbstractRepo<Purchase> {

    @Override
    public List<Purchase> findAll() {
        return super.findAll();
    }

    @PersistenceContext(unitName = "cooper")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseRepo() {
        super(Purchase.class);
    }
    
}
