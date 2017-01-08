package edu.ciukstar.cooper.application.admin;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author sergiu
 */
@RunWith(MockitoJUnitRunner.class)
public class JanitorTest {
        
    @Before
    public void setUp() {
    }

    @InjectMocks
    private Janitor janitor;
    
    @Test
    public void shouldUnregisterAUserSession() {
        final UserSession s = mock(UserSession.class);
        janitor.registerUserSession(s);
        
        janitor.unregisterUserSession(s);
        
        assertThat(janitor.getUserSessions(), not(hasItem(s)));
    }
    
    @Test
    public void shouldRegisterAUserSession() {
        final UserSession s = mock(UserSession.class);
        janitor.registerUserSession(s);
        
        assertThat(janitor.getUserSessions(), hasItem(s));
    }
    
}
