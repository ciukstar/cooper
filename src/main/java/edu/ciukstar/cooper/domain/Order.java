package edu.ciukstar.cooper.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "purchase_orders")
public class Order implements Serializable {

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "{Order_number_may_not_be_blank}")
    @Column(name = "order_number", nullable = false)
    private String number;
    
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id", nullable = false)
    private User customer;
    
    @ManyToOne
    @JoinColumn(name = "purchase", referencedColumnName = "id", nullable = false)
    private Purchase purchase;
    
    @NotNull(message = "{Issue_at_may_not_be_null}")    
    @Column(name = "issued_at", nullable = false)
    private LocalDateTime issuedAt;
    
    @NotNull(message = "{Quantity_may_not_be_null}")
    @Min(value = 1, message = "{Quantity_greater_or_equal_to_value}")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
}
