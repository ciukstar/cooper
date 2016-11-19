package edu.ciukstar.cooper.domain;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
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
public class EdgeTest {

    @Before
    public void setUp() {
        edge = Edge.source(mock(Status.class))
                .target(mock(Status.class)).graph(mock(Graph.class)).get();
    }

    private Edge edge;

    @Test
    public void shouldNotBeEqualIfBelogToDiffrentGraphs() {
        Edge other = Edge.source(edge.getSource()).target(edge.getTarget()).graph(mock(Graph.class)).get();
        
        assertThat(edge, is(not(equalTo(other))));
    }

    @Test
    public void shouldNotBeEqualIfTargetsAreDiffrent() {
        Edge other = Edge.source(edge.getSource()).target(mock(Status.class)).graph(edge.getGraph()).get();
        
        assertThat(edge, is(not(equalTo(other))));
    }

    @Test
    public void shouldNotBeEqualIfSourcesAreDiffrent() {
        Edge other = Edge.source(mock(Status.class)).target(edge.getTarget()).graph(edge.getGraph()).get();
        
        assertThat(edge, is(not(equalTo(other))));
    }

    @Test
    public void shouldBeEqualIfSourceAndTargetAreEqual() {
        Edge other = Edge.source(edge.getSource()).target(edge.getTarget()).graph(edge.getGraph()).get();
        
        assertThat(edge, is(equalTo(other)));
    }

}
