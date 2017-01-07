package edu.ciukstar.cooper.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "ORDERS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ORDER_NUMBER"})
    ,
    @UniqueConstraint(columnNames = {"CUSTOMER", "PURCHASE", "ORDER_DATE"})
})
public class Order implements Persistable<Long>, StatusTrackable {

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

    @ManyToOne
    @JoinColumn(name = "STATUS", referencedColumnName = "ID", nullable = false)
    private Status status;

    public Order(Status status) {
        this.status = status;
    }

    public Order() {
        this(null);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.number);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }

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

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

}
