package si.flexdetect.dataservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.flexdetect.dataservice.model.MeasurementFeature;
import si.flexdetect.dataservice.repository.MeasurementFeatureRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MeasurementFeatureService {

    private final MeasurementFeatureRepository measurementFeatureRepository;

    public MeasurementFeatureService(MeasurementFeatureRepository measurementFeatureRepository) {
        this.measurementFeatureRepository = measurementFeatureRepository;
    }

    public MeasurementFeature createMeasurementFeature(MeasurementFeature measurementFeature) {
        return measurementFeatureRepository.save(measurementFeature);
    }

    public Optional<MeasurementFeature> getMeasurementFeatureById(Long id) {
        return measurementFeatureRepository.findById(id);
    }
    public List<MeasurementFeature> getFeaturesForMeasurement(Long measurementId) {
        return measurementFeatureRepository.findByMeasurementId(measurementId);
    }

    public MeasurementFeature updateMeasurementFeature(Long id, MeasurementFeature updatedMeasurementFeature) {
        return measurementFeatureRepository.findById(id).map(measurementFeature ->  {
            measurementFeature.setName(updatedMeasurementFeature.getName());
            measurementFeature.setValue(updatedMeasurementFeature.getValue());
            return measurementFeatureRepository.save(measurementFeature);
        }).orElseThrow(() -> new RuntimeException("MeasurementFeature not found with id " + id));
    }

    public void deleteMeasurementFeatureById(Long id) {
        measurementFeatureRepository.deleteById(id);
    }
}
