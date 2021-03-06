package edu.ciukstar.cooper.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "CATEGORIES")
public class Category implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank(message = "{The_name_may_not_be_blank}")
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "SUBCATEGORIES",
            joinColumns = {
                @JoinColumn(name = "CATEGORY", nullable = false, referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "SUBCATEGORY", nullable = false, referencedColumnName = "ID")},
            uniqueConstraints = {
                @UniqueConstraint(columnNames = {"CATEGORY", "SUBCATEGORY"})})
    private List<Category> subcategories;

    public Category() {
    }

    public List<Category> getSubcategories() {
        return new ArrayList<>(subcategories);
    }
    
    public void addSubcategory(Category subcategory) {
        this.subcategories.add(subcategory);
    }
    
    public void removeSubcategory(Category subcategory) {
        this.subcategories.remove(subcategory);
    }

    @Override
    public String toString() {
        return "Category{" + "name=" + name + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.name);
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
        final Category other = (Category) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }

    public boolean hasSubcategories() {
        return !this.subcategories.isEmpty();
    }
    
}
