package si.flexdetect.dataservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import si.flexdetect.dataservice.model.Measurement;
import si.flexdetect.dataservice.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }
    public Measurement createMeasurement(Measurement measurement) {
        return measurementRepository.save(measurement);
    }
    public Optional<Measurement> getMeasurementById(Long id) {
        return measurementRepository.findById(id);
    }
    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }
    public Measurement updateMeasurement(Long id, Measurement updatedMeasurement) {
        return  measurementRepository.findById(id).map(
                measurement -> {
                    measurement.setTimestamp(updatedMeasurement.getTimestamp());
                    measurement.setPowerKw(updatedMeasurement.getPowerKw());
                    measurement.setDrFlag(updatedMeasurement.getDrFlag());
                    measurement.setDrCapacityKw(updatedMeasurement.getDrCapacityKw());
                    return measurementRepository.save(measurement);
                }
        ).orElseThrow(() -> new RuntimeException("Measurement not found with id " + id));
    }
    public void deleteMeasurement(Long id) {
        measurementRepository.deleteById(id);
    }
}
