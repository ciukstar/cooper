package edu.ciukstar.cooper.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "WAREHOUSES")
public class Warehouse implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank(message = "{The_name_may_not_be_blank}")
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @NotBlank(message = "{Address_may_not_be_blank}")
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "LATITUDE")
    private String latitude;
    @Column(name = "LONGITUDE")
    private String longitude;
    @Column(name = "GMAP_API_KEY")
    private String gmapApiKey;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getGmapApiKey() {
        return gmapApiKey;
    }

    public void setGmapApiKey(String gmapApiKey) {
        this.gmapApiKey = gmapApiKey;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
