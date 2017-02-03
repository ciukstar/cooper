package edu.ciukstar.cooper.application.cart;

import edu.ciukstar.cooper.domain.Article;
import edu.ciukstar.cooper.domain.Currency;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

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

    public Map<Currency, BigDecimal> getAmountByCurrency() {
        return articles.stream().collect(
                groupingBy(Article::getCurrency, 
                        reducing(BigDecimal.ZERO, Article::getPrice, BigDecimal::add)));
    }
    
    public BigDecimal getAmount() {
        return articles.stream().map(Article::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
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
    
    public boolean isEmpty() {
        return this.articles.isEmpty();
    }
}
