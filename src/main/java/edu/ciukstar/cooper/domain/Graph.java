package edu.ciukstar.cooper.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "graphs", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code"})})
public class Graph implements Persistable<Long>, Serializable {

    public static GraphBuilder.NameStep code(String code) {
        return GraphBuilder.code(code);
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{Code_many_not_be_blank}")
    @Column(name = "code", nullable = false)
    private String code;

    @NotBlank(message = "{The_name_may_not_be_blank}")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "start_status", referencedColumnName = "id")
    private Status startNode;

    @ManyToMany
    @JoinTable(
            name = "graph_nodes",
            joinColumns = {
                @JoinColumn(name = "graph", nullable = false, referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "node", nullable = false, referencedColumnName = "id")}
    )
    private Set<Status> nodes;
    @OneToMany(mappedBy = "graph")
    private Set<Vertex> vertices;

    public Graph() {
        this.vertices = new HashSet<>();
        this.nodes = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Graph{" + "code=" + code + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.code);
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
        final Graph other = (Graph) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStartNode() {
        return startNode;
    }

    public void setStartNode(Status startNode) {
        this.startNode = startNode;
    }

    public Set<Status> getNodes() {
        return new HashSet<>(nodes);
    }

    public Set<Vertex> getVertices() {
        return new HashSet<>(vertices);
    }

    public void addNode(Status node) {
        this.nodes.add(node);
    }

    public void removeNode(Status node) {
        this.nodes.remove(node);
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }

}