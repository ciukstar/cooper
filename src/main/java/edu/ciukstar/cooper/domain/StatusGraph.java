package edu.ciukstar.cooper.domain;

import java.io.Serializable;
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
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "status_graphs", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code"})})
public class StatusGraph implements Serializable {

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
    private Status startStatus;

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
        final StatusGraph other = (StatusGraph) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

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

    public Status getStartStatus() {
        return startStatus;
    }

    public void setStartStatus(Status startStatus) {
        this.startStatus = startStatus;
    }

}
