package edu.ciukstar.cooper.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "purchase_orders")
public class Order implements Serializable {

    public Long getId() {
        return id;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private User customer;
    
    private Purchase purchase;
    
    private LocalDateTime issuedAt;
    
    private Integer quantity;
}
