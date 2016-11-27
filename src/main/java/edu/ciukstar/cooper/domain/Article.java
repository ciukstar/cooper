package edu.ciukstar.cooper.domain;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "ARTICLES")
public class Article implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank(message = "{Number_may_not_be_blank}")
    @Column(name = "ARTICLE_NUMBER", nullable = false, unique = true)
    private String number;
    @ManyToOne
    @NotNull(message = "{Product_may_not_be_null}")
    @JoinColumn(name = "PRODUCT", nullable = false)
    private Product product;
    @ManyToOne
    @NotNull(message = "{Purchase_may_not_be_null}")
    @Column(name = "PURCHASE", nullable = false)
    private Purchase purchase;
    @NotNull(message = "{Price_may_not_be_null}")
    @Min(value = 0, message = "{Prive_should_be_greater_than_zero}")
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;
    @ManyToOne
    @NotNull(message = "{Currency_may_not_be_null}")
    @Column(name = "CURRENCY", nullable = false)
    private Currency currency;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.number);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }
    
}
