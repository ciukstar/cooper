package edu.ciukstar.cooper.application.graph;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Edge;
import edu.ciukstar.cooper.repo.CrudOperation;
import java.io.Serializable;
import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class EdgeCache extends CrudCache<Edge> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Edge> crudOperation;
    private Edge entity;    

    void refreshe(@Observes Set<Edge> input) {
        entity = refresher.match(entity, input).orElse(null);
    }
    
    @Override
    protected void setCrudOperation(CrudOperation<Edge> op) {
        this.crudOperation = op;
    }

    @Override
    protected CrudOperation<Edge> getCrudOperation() {
        return crudOperation;
    }

    @Override
    public void setEntity(Edge entity) {
        this.entity = entity;
    }

    @Override
    public Edge getEntity() {
        return this.entity;
    }

    
}
