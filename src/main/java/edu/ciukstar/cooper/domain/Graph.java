package edu.ciukstar.cooper.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
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
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "GRAPHS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODE"})})
public class Graph implements Persistable<Long>, Serializable {

    public static GraphBuilder.NameStep code(String code) {
        return GraphBuilder.code(code);
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank(message = "{Code_many_not_be_blank}")
    @Column(name = "CODE", nullable = false)
    private String code;

    @NotBlank(message = "{The_name_may_not_be_blank}")
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "START_STATUS", referencedColumnName = "ID")
    private Status startNode;

    @ManyToOne
    @JoinColumn(name = "END_STATUS", referencedColumnName = "ID")
    private Status endNode;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "graph_nodes",
            joinColumns = {
                @JoinColumn(name = "graph", nullable = false, referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "node", nullable = false, referencedColumnName = "id")}
    )
    private Set<Status> nodes;
    
    @OneToMany(mappedBy = "graph", fetch = FetchType.EAGER)
    private Set<Edge> edges;

    public Graph() {
        this.edges = new HashSet<>();
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

    public Status getEndNode() {
        return endNode;
    }

    public void setEndNode(Status endNode) {
        this.endNode = endNode;
    }

    public Set<Status> getNodes() {
        return new HashSet<>(nodes);
    }

    public Set<Edge> getEdges() {
        return new HashSet<>(edges);
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

    public void transition(StatusTrackable entity, Edge edge) {
        if (!edge.getSource().equals(entity.getStatus())) {
            throw new IllegalStateException(entity.getStatus().toString());
        }
        entity.setStatus(edge.getTarget());
    }

    public Set<Edge> getOutEdges(StatusTrackable entity) {
        return getEdges().stream().filter(e -> e.getSource().equals(entity.getStatus()))
                .collect(Collectors.toSet());
    }

    public Graph addEdge(Edge edge) {
        this.edges.add(edge);
        edge.setGraph(this);
        return this;
    }

    public boolean isStartNode(Status node) {
        return getStartNode().equals(node);
    }

}
