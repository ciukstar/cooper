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
@Table(name = "ORDERS")
public class Order implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank(message = "{Order_number_may_not_be_blank}")
    @Column(name = "ORDER_NUMBER", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER", referencedColumnName = "id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "PURCHASE", referencedColumnName = "id", nullable = false)
    private Purchase purchase;

    @NotNull(message = "{Order_date_may_not_be_null}")
    @Column(name = "ORDER_DATE", nullable = false)
    private LocalDateTime orderDate;

    @NotNull(message = "{Quantity_may_not_be_null}")
    @Min(value = 1, message = "{Quantity_greater_or_equal_to_value}")
    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

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

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }

}
