package si.flexdetect.dataservice.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "facilities_data")
public class FacilityData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facilities_data")
    private Long idFacilitiesData;

    private Instant timestamp;
    @Column(name = "data_json", columnDefinition = "json")
    private String dataJson;

    @ManyToOne
    @JoinColumn(name = "facilities_id_facilities", nullable = false)
    private Facility facility;

    public FacilityData() {}

    public Long getIdFacilitiesData() {
        return idFacilitiesData;
    }
    public void setIdFacilitiesData(Long idFacilitiesData) {
        this.idFacilitiesData = idFacilitiesData;
    }
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
    public String getDataJson() {
        return dataJson;
    }
    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }
    public Facility getFacility() {
        return facility;
    }
    public void setFacility(Facility facility) {
        this.facility = facility;
    }
}
