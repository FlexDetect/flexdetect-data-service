package si.flexdetect.dataservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facility", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "address", length = 45)
    private String address;

    @Column(name = "type", length = 45)
    private String type;

    @Column(name = "size", precision = 10)
    private BigDecimal size;

    @Column(name = "floors")
    private Integer floors;

    @Column(name = "contact_name", length = 45)
    private String contactName;

    @Column(name = "contact_phone", length = 45)
    private String contactPhone;

    @Column(name = "contact_email", length = 45)
    private String contactEmail;

    @OneToMany(mappedBy = "facility")
    @JsonManagedReference
    private Set<Dataset> datasets = new LinkedHashSet<>();



    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<Dataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(Set<Dataset> datasets) {
        this.datasets = datasets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
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