package edu.ciukstar.cooper.application;

import edu.ciukstar.cooper.domain.Graph;
import edu.ciukstar.cooper.domain.Persistable;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.Optional;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author sergiu
 */
@RunWith(MockitoJUnitRunner.class)
public class RefresherTest {
    
    @Before
    public void setUp() {
    }

    @InjectMocks
    private Refresher refresher;
    
    
    @Test
    public void shouldNotRefreshANewElement() {
        final Graph fresh = mock(Graph.class);
        final Graph current = mock(Graph.class);
        final Graph other = mock(Graph.class);
        final String code = "g1";
        
        when(fresh.getCode()).thenReturn(code);
        when(fresh.isNew()).thenReturn(Boolean.FALSE);
        
        when(current.getCode()).thenReturn(code);
        when(current.isNew()).thenReturn(Boolean.TRUE);
        
        Optional<Graph> result = refresher.match(current, asList(other, fresh));
        
        assertThat(result, is(Optional.of(current)));
    }
        
    @Test
    public void shouldReturnAnEmptyResultIfNoElementsToChooseFrom() {
        final Persistable<?> current = mock(Persistable.class);
        when(current.isNew()).thenReturn(Boolean.FALSE);
        
        Optional<Persistable<?>> result = refresher.match(current, Collections.EMPTY_LIST);
        
        assertThat(result, is(Optional.empty()));
    }
    
    @Test
    public void shouldChooseAFreshValueFromMultiple() {
        final Graph current = Graph.code("g1").name("G1").description("D1").get();
        final Graph fresh = Graph.code(current.getCode()).nameAsCode().noDescription().get();
        final Graph other = mock(Graph.class);
        
        Optional<Graph> result = refresher.match(current, asList(other, fresh));
        
        assertThat(result, is(Optional.of(fresh)));
    }
    
}
