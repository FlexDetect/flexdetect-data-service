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
    public Measurement create(Measurement measurement) {
        return measurementRepository.save(measurement);
    }
    public Measurement findById(Long id) {
        return measurementRepository.findById(id).orElse(null);
    }
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }
    public Measurement update(Long id, Measurement measurement) {
        return measurementRepository.findById(id).map(
                measurement1 -> {
                    TBCONTINUED
                }
        )
    }
}
