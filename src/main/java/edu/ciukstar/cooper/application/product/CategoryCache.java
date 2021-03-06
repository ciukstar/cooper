package edu.ciukstar.cooper.application.product;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Category;
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
public class CategoryCache extends CrudCache<Category> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Category> op;
    private Category entity;

    void refresh(@Observes List<Category> source) {
        this.entity = refresher.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Category> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Category> getCrudOperation() {
        return this.op;
    }

    @Override
    public void setEntity(Category entity) {
        this.entity = entity;
    }

    @Override
    public Category getEntity() {
        return entity;
    }

}
