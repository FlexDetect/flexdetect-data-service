package si.flexdetect.dataservice.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "facilities")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facilities")
    private Long idFacility;

    private String name;

    private String address;

    private Double latitude;

    private Double longitude;

    private String type;

    private Double size;

    private Integer floors;

    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "contact_phone")
    private String contactPhone;
    @Column(name = "contact_email")
    private String contactEmail;
    // OneToMany povezava do Measurement
    @OneToMany(mappedBy = "facilities", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Measurement> measurements;

    public Facility() {}

    public Long getIdFacility() {
        return idFacility;
    }
    public void setIdFacility(Long idFacility) {
        this.idFacility = idFacility;
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
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Double getSize() {
        return size;
    }
    public void setSize(Double size) {
        this.size = size;
    }
    public Integer getFloors() {
        return floors;
    }
    public void setFloors(Integer floors) {
        this.floors = floors;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}

