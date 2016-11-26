package edu.ciukstar.cooper.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "STATUSES", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODE"}),
    @UniqueConstraint(columnNames = {"NAME"})
})
public class Status implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotEmpty(message = "{Code_may_not_be_empty}")
    @Column(name = "CODE", nullable = false)
    private String code;
    
    @NotEmpty(message = "{The_name_may_not_be_empty}")
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;
    
    @Column(name = "ICON", nullable = true)
    private String icon;

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.code);
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
        final Status other = (Status) obj;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }
    
}
