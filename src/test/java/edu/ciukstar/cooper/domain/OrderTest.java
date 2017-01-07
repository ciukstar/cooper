package edu.ciukstar.cooper.domain;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.not;
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
        final Article article = new Article();
        order.addArticle(article);
        
        order.removeArticle(article);
        
        assertThat(order.getArticles(), not(hasItem(article)));
        assertThat(article.getOrder(), is(nullValue()));
    }
    
    @Test
    public void shouldAddAnArticle() {
        final Article article = new Article();
        
        order.addArticle(article);
        
        assertThat(order.getArticles(), hasItem(article));
        assertThat(article.getOrder(), is(order));
    }
    
}
