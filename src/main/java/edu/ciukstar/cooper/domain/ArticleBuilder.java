package edu.ciukstar.cooper.domain;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class ArticleBuilder {

    public Article forPurchase(Purchase purchase) {
        Article article = emptyArticle();
        article.setPurchase(purchase);
        return article;
    }

    public Article forProduct(Product product) {
        Article article = emptyArticle();
        article.setProduct(product);
        return article;
    }
    
    public Article emptyArticle() {
        return new Article();
    }
    
}
