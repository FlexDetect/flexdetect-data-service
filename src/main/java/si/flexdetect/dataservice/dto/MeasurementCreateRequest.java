package si.flexdetect.dataservice.dto;

import java.time.Instant;

public class MeasurementCreateRequest {
    public Integer measurementNameId;
    public Instant timestamp;
    public Integer valueInt;
    public Float valueFloat;
    public Byte valueBool;

    public MeasurementCreateRequest() {
        // potreben prazen konstruktor za Jackson
    }

    public Integer getMeasurementNameId() {
        return measurementNameId;
    }
    public void setMeasurementNameId(Integer measurementNameId) {
        this.measurementNameId = measurementNameId;
    }
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
    public Integer getValueInt() {
        return valueInt;
    }
    public void setValueInt(Integer valueInt) {
        this.valueInt = valueInt;
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
}
