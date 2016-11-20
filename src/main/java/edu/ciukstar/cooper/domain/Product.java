package edu.ciukstar.cooper.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Manufacturer getVender() {
        return vender;
    }

    public void setVender(Manufacturer vender) {
        this.vender = vender;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "{Code_may_not_be_blank}")
    @Column(name = "code", nullable = false)
    private String code;
    @NotBlank(message = "{Name_may_not_be_blank}")
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @NotNull(message = "{Price_may_not_be_null}")
    @Min(value = 0, message = "{Price_must_be_greater_or_equal_to_value}")
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Lob
    private byte[] photo;
    @ManyToOne
    @JoinColumn(name = "vender", referencedColumnName = "id")
    private Manufacturer vender;
}
