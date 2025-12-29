package si.flexdetect.dataservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_measurement", nullable = false)
    private Integer id;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @Column(name = "value_float")
    private Float valueFloat;

    @Column(name = "value_bool")
    private Byte valueBool;

    @Column(name = "value_int")
    private Integer valueInt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dataset_id_dataset", nullable = false)
    @JsonBackReference
    private Dataset dataset;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "measurement_name_id_measurement_name", nullable = false)
    @JsonBackReference
    private MeasurementName measurementNameIdMeasurementName;

    public MeasurementName getMeasurementNameIdMeasurementName() {
        return measurementNameIdMeasurementName;
    }

    public void setMeasurementNameIdMeasurementName(MeasurementName measurementNameIdMeasurementName) {
        this.measurementNameIdMeasurementName = measurementNameIdMeasurementName;
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