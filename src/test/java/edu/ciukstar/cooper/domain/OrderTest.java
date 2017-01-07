package edu.ciukstar.cooper.domain;

import static org.hamcrest.Matchers.hasItem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author sergiu
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderTest {
    
    @Before
    public void setUp() {
    }

    @InjectMocks
    private Order order;
    
    @Test
    public void shouldRemoveAnArticle() {
        final Article article = mock(Article.class);
        order.addArticle(article);
        
        order.removeArticle(article);
    }
    
    @Test
    public void shouldAddAnArticle() {
        final Article article = mock(Article.class);
        
        order.addArticle(article);
        
        assertThat(order.getArticles(), hasItem(article));
    }
    
}
