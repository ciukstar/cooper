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
@Table(name = "resources")
public class Resource implements Persistable<Long>, StatusTrackable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{Name_may_not_be_blank}")
    @Column(name = "NAME", nullable = false)
    private String name;
    @NotNull(message = "{Role_may_not_be_null}")
    @JoinColumn(name = "ROLE", nullable = false, referencedColumnName = "ID")
    private Role role;
    @ManyToOne
    @NotNull(message = "{Status_may_not_be_null}")
    @JoinColumn(name = "STATUS", nullable = false, referencedColumnName = "ID")
    private Status status;

    Resource(Role role) {
        this.role = role;
    }

    public Resource() {
        this(null);
    }

    @Override
    public String toString() {
        return "Resource{" + "name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.name);
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
        final Resource other = (Resource) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }
    
}
