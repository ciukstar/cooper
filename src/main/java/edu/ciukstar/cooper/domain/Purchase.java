package edu.ciukstar.cooper.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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

    @NotNull(message = "{Organization_cost_may_not_be_null}")
    @Min(value = 0, message = "{Organization_cost_must_be_greater_than_value}")
    @Column(name = "ORGANIZATION_COST_AS_COEFF")
    private BigDecimal organizationCostAsCoefficient;

    @ManyToOne
    @NotNull(message = "{Status_may_not_be_null}")
    @JoinColumn(name = "STATUS", referencedColumnName = "id", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "PURCHASE")
    private List<Article> articles;

    @OneToMany(mappedBy = "PURCHASE")
    private List<Dispenser> dispensers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PURCHASE_RULES",
            joinColumns = {
                @JoinColumn(name = "PURCHASE", nullable = false, referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "RULE", nullable = false, referencedColumnName = "ID")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"PURCHASE", "RULE"})})
    private Set<Rule> rules;

    public Purchase(Status status) {
        this.rules = new HashSet<>();
        this.dispensers = new ArrayList<>();
        this.articles = new ArrayList<>();
        this.status = status;
    }

    public Purchase() {
        this(null);
    }

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

    public List<Article> getArticles() {
        return new ArrayList<>(articles);
    }

    public List<Dispenser> getDispensers() {
        return new ArrayList<>(dispensers);
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

    public List<Rule> getRules() {
        return new ArrayList<>(rules);
    }

    public void removeRule(Rule rule) {
        this.rules.remove(rule);
    }
    
    public void addRule(Rule rule) {
        this.rules.add(rule);
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
