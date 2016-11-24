package edu.ciukstar.cooper.domain;

import java.math.BigDecimal;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "PURCHASES")
public class Purchase implements Persistable<Long>, StatusTrackable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @NotNull(message = "{Organizer_may_not_be_null}")
    @JoinColumn(name = "ORGANIZER", referencedColumnName = "id", nullable = false)
    private User organizer;

    @ManyToOne
    @NotNull(message = "{Product_may_not_be_null}")
    @JoinColumn(name = "PRODUCT", referencedColumnName = "id", nullable = false)
    private Product product;

    @NotBlank(message = "{Code_may_not_be_blank}")
    @Column(name = "CODE", nullable = false, unique = true)
    private String code;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "STARTED_AT", nullable = true)
    private LocalDateTime startedAt;

    @Column(name = "CLOSED_AT", nullable = true)
    private LocalDateTime closedAt;

    @NotNull(message = "{Minimum_price_may_not_be_null}")
    @Min(value = 0, message = "{Minimum_price_must_be_greater_than_value}")
    @Column(name = "MIN_PRICE", nullable = false)
    private BigDecimal minimumPrice;

    @Column(name = "ORGANIZATION_COST_AS_COEFF")
    private BigDecimal organizationCostAsCoefficient;

    @ManyToOne
    @NotNull(message = "{Status_may_not_be_null}")
    @JoinColumn(name = "STATUS", referencedColumnName = "id", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "DISPENCER", referencedColumnName = "id", nullable = false)
    private Dispencer dispencer;

    @Override
    public String toString() {
        return "Purchase{" + "code=" + code + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.code);
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
        final Purchase other = (Purchase) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public Long getId() {
        return id;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean isNew() {
        return null == getId();
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

}
