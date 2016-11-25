package edu.ciukstar.cooper.application.graph;

import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.domain.Graph;
import edu.ciukstar.cooper.domain.Status;
import edu.ciukstar.cooper.repo.CrudOperation;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class GraphCache extends CrudCache<Graph> implements Serializable {

    @Inject
    private Refresher refresher;

    private CrudOperation crudOperation;
    private Graph entity;

    @PostConstruct
    public void buildDiagram() {

    }

    void refresh(@Observes List<Graph> source) {
        entity = refresher.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Graph> op) {
        this.crudOperation = op;
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

    public void addNodeToGraph(Status node) {
        this.entity.addNode(node);
    }

    public void removeNodeFromGraph(Status node) {
        this.entity.removeNode(node);
    }

}
