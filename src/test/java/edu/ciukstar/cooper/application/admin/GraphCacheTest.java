package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.domain.Graph;
import edu.ciukstar.cooper.domain.Status;
import edu.ciukstar.cooper.repo.GraphRepo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author sergiu
 */
@RunWith(MockitoJUnitRunner.class)
public class GraphCacheTest {
    
    @Before
    public void setUp() {
    }

    @Mock
    private GraphRepo repo;
    @Mock
    private Graph entity;    
    @InjectMocks
    private GraphCache cache;
    
    @Test
    public void shouldRemoveANodeFromGraph() {
        final Status node = mock(Status.class);
        
        cache.removeNodeFromGraph(node);
        
        verify(entity).removeNode(node);
    }
    
    @Test
    public void shouldAddANodeToGraph() {
        final Status node = mock(Status.class);
        
        cache.addNodeToGraph(node);
        
        verify(entity).addNode(node);
    }
    
}
