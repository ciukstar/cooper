package edu.ciukstar.cooper.application.cart;

import edu.ciukstar.cooper.domain.Article;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class Cart implements Serializable {

    private List<Article> articles;

    public Cart() {
        this.articles = new ArrayList<>();
    }

    public boolean contains(Article article) {
        return this.articles.contains(article);
    }
    
    public void add(Article article) {
        this.articles.add(article);
    }
    
    public void remove(Article article) {
        this.articles.remove(article);
    }
    
    public List<Article> getArticles() {
        return new ArrayList<>(articles);
    }
    
    public String getSize() {
        return String.valueOf(articles.size());
    }
}
