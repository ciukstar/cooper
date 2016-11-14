package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.domain.Status;
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
public class StatusCache extends CrudCache<Status> implements Serializable {

    private CrudOperation op;
    private Status entity;

    @Override
    protected void setCrudOperation(CrudOperation<Status> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Status> getCrudOperation() {
        return op;
    }

    @Override
    public void setEntity(Status entity) {        
        this.entity = entity;
    }

    @Override
    public Status getEntity() {
        return entity;
    }
    
}
