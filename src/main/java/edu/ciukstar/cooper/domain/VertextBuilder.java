package edu.ciukstar.cooper.domain;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class VertextBuilder {

    public Vertex emptyVertexForGraph(Graph graph) {
        return new Vertex(graph);
    }
    
}
