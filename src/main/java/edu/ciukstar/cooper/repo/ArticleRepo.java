package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Article;
import edu.ciukstar.cooper.domain.Article_;
import edu.ciukstar.cooper.domain.Purchase;
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
public class ArticleRepo extends AbstractRepo<Article> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Article>> e;

    public List<Article> findByPurchase(Purchase purchase) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Article> cq = cb.createQuery(Article.class);
        Root<Article> article = cq.from(Article.class);
        cq.select(article).where(cb.equal(article.get(Article_.purchase), purchase));
        return getEntityManager().createQuery(cq).getResultList();
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
