package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.domain.Graph;
import edu.ciukstar.cooper.repo.CrudOperation;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class GraphCache extends CrudCache<Graph> implements Serializable {

    private CrudOperation crudOperation;
    private Graph entity;

    @Override
    protected void setCrudOperation(CrudOperation<Graph> op) {
        this.crudOperation = op;
        this.entity = op.getEntity();
    }

    @Override
    protected CrudOperation<Graph> getCrudOperation() {
        return crudOperation;
    }

    @Override
    public void setEntity(Graph entity) {
        this.entity = entity;
    }

    @Override
    public Graph getEntity() {
        return this.entity;
    }
    
}
