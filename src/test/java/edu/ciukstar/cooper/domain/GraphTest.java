package edu.ciukstar.cooper.domain;

import java.util.Set;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author sergiu
 */
public class GraphTest {

    @Before
    public void setUp() {
        this.graph = new Graph();
        
        this.entity = new StatusTrackable() {
            private Status status;

            @Override
            public Status getStatus() {
                return this.status;
            }

            @Override
            public void setStatus(Status status) {
                this.status = status;
            }

        };
    }

    private Graph graph;
    private StatusTrackable entity;

    final Status s1 = mock(Status.class);
    final Status s2 = mock(Status.class);
    final Graph g = mock(Graph.class);
    final Edge e12 = Edge.source(s1).target(s2).graph(g).get();
    final Edge e21 = Edge.source(s2).target(s1).graph(g).get();

    @Test(expected = IllegalStateException.class)
    public void shouldThrowAnExceptionIfTheEntityIsNotInExpectedState() {

        graph.addEdge(e12);

        entity.setStatus(s2);

        graph.transition(entity, e12);
    }

    @Test
    public void shouldTransitionEntityStatus() {

        graph.addEdge(e12);

        entity.setStatus(s1);

        graph.transition(entity, e12);

        assertThat(entity.getStatus(), is(s2));
    }

    @Test
    public void shouldExtractAllOutgoingEdgesOfAGivenNode() {

        entity.setStatus(s1);

        this.graph.addEdge(e12).addEdge(e21);
        
        Set<Edge> res = graph.getOutEdges(entity);
        
        assertThat(res, hasItem(e12));
        assertThat(res.size(), is(1));
    }

    @Test
    public void indirectAdditionShouldHaveNoEffect() {
        final Status node = mock(Status.class);

        graph.getNodes().add(node);

        assertThat(graph.getNodes(), not(hasItem(node)));
    }

    @Test
    public void indirectRemovalShouldHaveNoEffect() {
        final Status node = mock(Status.class);
        graph.addNode(node);

        graph.getNodes().remove(node);

        assertThat(graph.getNodes(), hasItem(node));
    }

    @Test
    public void shouldRemoveANodeFromGraph() {
        final Status node = mock(Status.class);
        graph.addNode(node);

        graph.removeNode(node);

        assertThat(graph.getNodes(), not(hasItem(node)));
    }

    @Test
    public void shouldAddANodeToGraph() {
        final Status node = mock(Status.class);

        graph.addNode(node);

        assertThat(graph.getNodes(), hasItem(node));
    }

}
