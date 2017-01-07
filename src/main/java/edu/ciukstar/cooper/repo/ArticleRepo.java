package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Article;
import edu.ciukstar.cooper.domain.Order;
import edu.ciukstar.cooper.domain.Purchase;
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
public class ArticleRepo extends AbstractRepo<Article> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Article>> e;

    public List<Article> findByOrder(Order order) {
        return findAll().stream()
                .filter(a -> order.equals(a.getOrder())).collect(Collectors.toList());
    }

    public List<Article> findByPurchase(Purchase purchase) {
        List<Article> res = findAll().stream()
                .filter(a -> purchase.equals(a.getPurchase())).collect(Collectors.toList());
        e.fire(res);
        return res;
    }

    @Override
    public List<Article> findAll() {
        final List<Article> res = super.findAll();
        e.fire(res);
        return res;
    }

    public ArticleRepo() {
        super(Article.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
