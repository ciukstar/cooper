package edu.ciukstar.cooper.application.purchase;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Article;
import edu.ciukstar.cooper.repo.CrudOperation;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class ArticleCache extends CrudCache<Article> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Article> op;
    private Article entity;

    void refresh(@Observes List<Article> source) {
        entity = refresher.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Article> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Article> getCrudOperation() {
        return this.op;
    }

    @Override
    public void setEntity(Article entity) {
        this.entity = entity;
    }

    @Override
    public Article getEntity() {
        return this.entity;
    }

}
