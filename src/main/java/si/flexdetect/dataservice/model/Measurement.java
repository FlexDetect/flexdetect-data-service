package si.flexdetect.dataservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_measurement")
    private Integer id;

    private Instant timestamp;

    private Float valueFloat;
    private Integer valueInt;
    private Byte valueBool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dataset_id_dataset", nullable = false)
    @JsonIgnore
    private Dataset dataset;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "measurement_name_id_measurement_name", nullable = false)
    private MeasurementName measurementName;

    public MeasurementName getMeasurementNameIdMeasurementName() {
        return measurementName;
    }

    public void setMeasurementNameIdMeasurementName(MeasurementName measurementNameIdMeasurementName) {
        this.measurementName = measurementNameIdMeasurementName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Float getValueFloat() {
        return valueFloat;
    }

    public void setValueFloat(Float valueFloat) {
        this.valueFloat = valueFloat;
    }

    public Byte getValueBool() {
        return valueBool;
    }

    public void setValueBool(Byte valueBool) {
        this.valueBool = valueBool;
    }

    public Integer getValueInt() {
        return valueInt;
    }

    public void setValueInt(Integer valueInt) {
        this.valueInt = valueInt;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

}