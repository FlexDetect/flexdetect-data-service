package si.flexdetect.dataservice.dto;

import java.time.Instant;
import java.util.List;

public class MeasurementBulkCreateRequest {
    public MeasurementBulkCreateRequest() {

    }

    private List<Row> rows;

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public static class Row {
        private Integer measurementNameId;
        private Instant timestamp;

        private Integer valueInt;
        private Double valueFloat;
        private Byte valueBool;

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

        public Double getValueFloat() {
            return valueFloat;
        }

        public void setValueFloat(Double valueFloat) {
            this.valueFloat = valueFloat;
        }

        public Byte getValueBool() {
            return valueBool;
        }

        public void setValueBool(Byte valueBool) {
            this.valueBool = valueBool;
        }
    }
}
