package edu.ciukstar.cooper.application.product;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.domain.Product;
import edu.ciukstar.cooper.repo.CrudOperation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class ProductCache extends CrudCache<Product> implements Serializable {

    private CrudOperation<Product> op;
    private Product entity;

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
