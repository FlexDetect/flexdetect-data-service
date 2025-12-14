package si.flexdetect.dataservice.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_measurement_data", nullable = false)
    private Long id;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @Column(name = "power_kw")
    private Float powerKw;

    @Column(name = "dr_flag")
    private Byte drFlag;

    @Column(name = "dr_capacity_kw")
    private Float drCapacityKw;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "facilities_id_facilities", nullable = false)
    private Facility facility;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Float getPowerKw() {
        return powerKw;
    }

    public void setPowerKw(Float powerKw) {
        this.powerKw = powerKw;
    }

    public Byte getDrFlag() {
        return drFlag;
    }

    public void setDrFlag(Byte drFlag) {
        this.drFlag = drFlag;
    }

    public Float getDrCapacityKw() {
        return drCapacityKw;
    }

    public void setDrCapacityKw(Float drCapacityKw) {
        this.drCapacityKw = drCapacityKw;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

}