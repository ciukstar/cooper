package edu.ciukstar.cooper.application.admin;

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
public class GraphCache implements Serializable {

    private CrudOperation crudOperation;
    private Graph graph;

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
    
}
