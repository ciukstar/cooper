package edu.ciukstar.cooper.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "edges")
public class Edge implements Persistable<Long> {

    public static EdgeBuilder.TargetStep source(Status s) {
        return EdgeBuilder.source(s);
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @NotNull(message = "{Graph_may_not_be_null}")
    @JoinColumn(name = "graph", nullable = false, referencedColumnName = "id")
    private Graph graph;
    @ManyToOne
    @NotNull(message = "{Source_may_not_be_null}")
    @JoinColumn(name = "source", referencedColumnName = "id", nullable = false)
    private Status source;
    @ManyToOne
    @NotNull(message = "{Target_may_not_be_null}")
    @JoinColumn(name = "target", referencedColumnName = "id", nullable = false)
    private Status target;
    @NotBlank(message = "{Label_may_not_be_blank}")
    @Column(name = "label", nullable = false)
    private String label;
    @NotBlank(message = "{Transition_name_may_not_be_blank}")
    @Column(name = "transition_name", nullable = false)
    private String transitionName;
    @Column(name = "icon")
    private String icon;
    @Column(name = "description")
    private String description;

    Edge(Graph graph) {
        this.graph = graph;
    }

    public Edge() {
        this(null);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.graph);
        hash = 83 * hash + Objects.hashCode(this.source);
        hash = 83 * hash + Objects.hashCode(this.target);
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
        final Edge other = (Edge) obj;
        if (!Objects.equals(this.graph, other.graph)) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        if (!Objects.equals(this.target, other.target)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public Long getId() {
        return id;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Status getSource() {
        return source;
    }

    public void setSource(Status source) {
        this.source = source;
    }

    public Status getTarget() {
        return target;
    }

    public void setTarget(Status target) {
        this.target = target;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTransitionName() {
        return transitionName;
    }

    public void setTransitionName(String transitionName) {
        this.transitionName = transitionName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }

}