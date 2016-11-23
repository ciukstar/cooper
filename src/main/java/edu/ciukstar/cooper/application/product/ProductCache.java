package edu.ciukstar.cooper.application.product;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Product;
import edu.ciukstar.cooper.repo.CrudOperation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class ProductCache extends CrudCache<Product> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Product> op;
    private Product entity;

    void refresh(@Observes List<Product> source) {
        this.entity = refresher.match(entity, source).orElse(null);
    }

    public void uploadImage(FileUploadEvent e) {
        entity.setImage(e.getFile().getContents());
    }

    @Override
    protected void setCrudOperation(CrudOperation<Product> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Product> getCrudOperation() {
        return op;
    }

    @Override
    public void setEntity(Product entity) {
        this.entity = entity;
    }

    @Override
    public Product getEntity() {
        return entity;
    }

}
