package edu.ciukstar.cooper.domain;

import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "ROLES", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NAME"})
})
public class Role implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank(message = "{Name_may_not_be_blank}")
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull(message = "{Type_may_not_be_null}")
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private RoleType type;

    @ManyToOne
    @NotNull(message = "{Resource_status_graph_may_not_be_null}")
    @JoinColumn(name = "RESOURCE_STATUS_GRAPH", nullable = false, referencedColumnName = "ID")
    private Graph resourceStatusGraph;

    @ManyToOne
    @JoinColumn(name = "USER_STATUS_GRAPH", referencedColumnName = "ID")
    private Graph userStatusGraph;

    @ManyToOne
    @JoinColumn(name = "PURCHASE_STATUS_GRAPH", referencedColumnName = "ID")
    private Graph purchaseStatusGraph;

    @ManyToOne
    @JoinColumn(name = "ORDER_STATUS_GRAPH", referencedColumnName = "ID")
    private Graph orderStatusGraph;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Lob
    private byte[] image;

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public Graph getResourceStatusGraph() {
        return resourceStatusGraph;
    }

    public void setResourceStatusGraph(Graph resourceStatusGraph) {
        this.resourceStatusGraph = resourceStatusGraph;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Optional<Graph> userStatusGraph() {
        return Optional.ofNullable(userStatusGraph);
    }
    
    public Graph getUserStatusGraph() {
        return userStatusGraph;
    }

    public void setUserStatusGraph(Graph userStatusGraph) {
        this.userStatusGraph = userStatusGraph;
    }

    public Optional<Graph> purchaseStatusGraph() {
        return Optional.ofNullable(purchaseStatusGraph);
    }
    
    public Graph getPurchaseStatusGraph() {
        return purchaseStatusGraph;
    }

    public void setPurchaseStatusGraph(Graph purchaseStatusGraph) {
        this.purchaseStatusGraph = purchaseStatusGraph;
    }

    public Optional<Graph> orderStatusGraph() {
        return Optional.ofNullable(orderStatusGraph);
    }
    
    public Graph getOrderStatusGraph() {
        return orderStatusGraph;
    }

    public void setOrderStatusGraph(Graph orderStatusGraph) {
        this.orderStatusGraph = orderStatusGraph;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }
}
