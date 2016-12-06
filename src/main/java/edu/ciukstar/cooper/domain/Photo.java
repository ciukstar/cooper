package edu.ciukstar.cooper.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "PRODUCT_IMAGES")
public class Photo implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Lob
    @Column(name = "RAW")
    private byte[] raw;

    @Column(name = "MIME")
    private String mime;

    @Column(name = "EXT")
    private String ext;

    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public String toString() {
        return "ProductImage{" + "mime=" + mime + ", ext=" + ext + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Photo other = (Photo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public byte[] getRaw() {
        return raw;
    }

    public void setRaw(byte[] raw) {
        this.raw = raw;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
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

}
