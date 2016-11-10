package edu.ciukstar.cooper.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "purchases")
public class Purchase implements Serializable {

    public Long getId() {
        return id;
    }

    public User getOrganiser() {
        return organiser;
    }

    public void setOrganiser(User organiser) {
        this.organiser = organiser;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
    }

    public Dispencer getDispencer() {
        return dispencer;
    }

    public void setDispencer(Dispencer dispencer) {
        this.dispencer = dispencer;
    }

    public BigDecimal getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(BigDecimal minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public BigDecimal getOrganizationCostAsCoefficient() {
        return organizationCostAsCoefficient;
    }

    public void setOrganizationCostAsCoefficient(BigDecimal organizationCostAsCoefficient) {
        this.organizationCostAsCoefficient = organizationCostAsCoefficient;
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull(message = "{Organizer_may_not_be_null}")
    @JoinColumn(name = "organiser", referencedColumnName = "id", nullable = false)
    private User organiser;
    
    @ManyToOne
    @NotNull(message = "{Product_may_not_be_null}")
    @JoinColumn(name = "product", referencedColumnName = "id", nullable = false)
    private Product product;
    
    @Column(name = "started_at", nullable = true)
    private LocalDateTime startedAt;
    
    @Column(name = "closed_at", nullable = true)
    private LocalDateTime closedAt;
    
    @NotNull(message = "{Minimum_price_may_not_be_null}")
    @Min(value = 0, message = "{Minimum_price_must_be_greater_than_value}")
    @Column(name = "min_price", nullable = false)
    private BigDecimal minimumPrice;
    
    @Column(name = "organization_cost_as_coeff")
    private BigDecimal organizationCostAsCoefficient;
    
    @ManyToOne
    @NotNull(message = "{Status_may_not_be_null}")
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "dispencer", referencedColumnName = "id", nullable = false)
    private Dispencer dispencer;
}
