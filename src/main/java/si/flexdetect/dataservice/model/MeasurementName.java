package si.flexdetect.dataservice.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "measurement_name")
public class MeasurementName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_measurement_name", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "unit", length = 45)
    private String unit;

    @Column(name = "data_type", length = 45)
    private String dataType;

    @OneToMany
    @JoinColumn(name = "measurement_name_id_measurement_name")
    private Set<Measurement> measurements = new LinkedHashSet<>();

    public Set<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Set<Measurement> measurements) {
        this.measurements = measurements;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}