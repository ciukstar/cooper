package edu.ciukstar.cooper.domain;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

/**
 *
 * @author sergiu
 */
public class GraphTest {

    @Before
    public void setUp() {
        this.graph = new Graph();
    }

    private Graph graph;

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
