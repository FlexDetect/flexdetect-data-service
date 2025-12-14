package si.flexdetect.dataservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "measurement_feature")
public class MeasurementFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_measurement_feature", nullable = false)
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "value")
    private Float value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    @JoinColumn(name = "measurement_id_measurement_data", nullable = false)
    private Measurement measurement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurementIdMeasurementData) {
        this.measurement = measurementIdMeasurementData;
    }
}