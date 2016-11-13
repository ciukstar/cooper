package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.domain.StatusGraph;
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
public class GraphCache implements Serializable {

    private CrudOperation crudOperation;
    private StatusGraph graph;

    public StatusGraph getGraph() {
        return graph;
    }

    public void setGraph(StatusGraph graph) {
        this.graph = graph;
    }
    
}
