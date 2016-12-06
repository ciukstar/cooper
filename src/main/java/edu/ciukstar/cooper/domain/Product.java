package edu.ciukstar.cooper.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Lob;
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
@Table(name = "PRODUCTS")
public class Product implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @NotBlank(message = "{Code_may_not_be_blank}")
    @Column(name = "CODE", nullable = false)
    private String code;
    @NotBlank(message = "{Name_may_not_be_blank}")
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Lob
    @Column(name = "IMAGE")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "CATEGORY", nullable = false, referencedColumnName = "ID")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "MANUFACTURER", referencedColumnName = "ID")
    private Manufacturer manufacturer;
    @ManyToOne
    @JoinColumn(name = "COUNTRY", referencedColumnName = "ID")
    private Country country;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PRODUCT_PHOTOS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"PRODUCT", "PHOTO"})},
            joinColumns = {
                @JoinColumn(name = "PRODUCT", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "PHOTO", nullable = false)})
    private Set<Photo> photos;

    public Product() {
        this.photos = new HashSet<>();
    }

    @Override
    public String toString() {
        return Arrays.asList(getCode(), getName()).stream()
                .collect(Collectors.joining(": "));
    }

    public static ProductBuilder.CodeStep from() {
        return ProductBuilder.from();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.code);
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
        final Product other = (Product) obj;
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

    public List<Photo> getPhotos() {
        return new ArrayList<>(photos);
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }

    public void addPhoto(Photo photo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
