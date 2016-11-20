package edu.ciukstar.cooper.application.product;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.domain.Manufacturer;
import edu.ciukstar.cooper.repo.CrudOperation;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class ManufacturerCache extends CrudCache<Manufacturer> implements Serializable {

    private CrudOperation<Manufacturer> op;
    private Manufacturer entity;

    @Override
    protected void setCrudOperation(CrudOperation<Manufacturer> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Manufacturer> getCrudOperation() {
        return op;
    }

    @Override
    public void setEntity(Manufacturer entity) {
        this.entity = entity;
    }

    @Override
    public Manufacturer getEntity() {
        return entity;
    }

}
