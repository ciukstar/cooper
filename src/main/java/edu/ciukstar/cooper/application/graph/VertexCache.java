package edu.ciukstar.cooper.application.graph;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Vertex;
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
public class VertexCache extends CrudCache<Vertex> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Vertex> crudOperation;
    private Vertex entity;    

    void refreshe(@Observes Set<Vertex> input) {
        entity = refresher.match(entity, input).orElse(null);
    }
    
    @Override
    protected void setCrudOperation(CrudOperation<Vertex> op) {
        this.crudOperation = op;
    }

    @Override
    protected CrudOperation<Vertex> getCrudOperation() {
        return crudOperation;
    }

    @Override
    public void setEntity(Vertex entity) {
        this.entity = entity;
    }

    @Override
    public Vertex getEntity() {
        return this.entity;
    }

    
}
