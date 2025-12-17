package si.flexdetect.dataservice.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import si.flexdetect.dataservice.model.MeasurementName;
import si.flexdetect.dataservice.repository.MeasurementNameRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MeasurementNameService {

    private MeasurementNameRepository measurementNameRepository;

    public MeasurementNameService(MeasurementNameRepository measurementNameRepository) {
        this.measurementNameRepository = measurementNameRepository;
    }

    public MeasurementName createMeasurementName(MeasurementName measurementName) {
        return measurementNameRepository.save(measurementName);
    }

    public Optional<MeasurementName> getMeasurementNameById(Integer id) {
        return measurementNameRepository.findById(id);
    }

    public List<MeasurementName> getAllMeasurementNames() {
        return measurementNameRepository.findAll();
    }

    public MeasurementName updateMeasurementName(Integer id, MeasurementName updatedMeasurementName) {
        return measurementNameRepository.findById(id).map(measurementName -> {
            measurementName.setName(updatedMeasurementName.getName());
            measurementName.setUnit(updatedMeasurementName.getUnit());
            measurementName.setDataType(updatedMeasurementName.getDataType());
            return measurementNameRepository.save(measurementName);
        }).orElseThrow(() -> new RuntimeException("MeasurementName not found with " + id));
    }

    public void deleteMeasurementName(Integer id) {
        measurementNameRepository.deleteById(id);
    }
}
