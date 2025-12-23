package si.flexdetect.dataservice.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.flexdetect.dataservice.model.Measurement;
import si.flexdetect.dataservice.repository.DatasetRepository;
import si.flexdetect.dataservice.repository.MeasurementRepository;
import si.flexdetect.dataservice.security.SecurityUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final DatasetRepository datasetRepository;

    public MeasurementService(MeasurementRepository measurementRepository, DatasetRepository datasetRepository) {
        this.measurementRepository = measurementRepository;
        this.datasetRepository = datasetRepository;
    }

    public Measurement createMeasurement(Measurement measurement) {
        Integer userId = SecurityUtils.userId();
        Integer datasetId = measurement.getDataset().getId();
        boolean allowed =
                datasetRepository.existsByIdAndFacility_UserId(datasetId, userId);

        if (!allowed) {
            throw new AccessDeniedException("Dataset not owned by user");
        }
        return measurementRepository.save(measurement);
    }

    public List<Measurement> getMeasurementByDatasetId(Integer datasetId) {
        Integer userId = SecurityUtils.userId();
        return measurementRepository.findByDataset_IdAndDataset_Facility_UserId(datasetId, userId);
    }

    public Optional<Measurement> getMeasurementById(Integer id) {
        Integer userId = SecurityUtils.userId();
        return measurementRepository.findByIdAndDataset_Facility_UserId(id, userId);
    }

    public List<Measurement> getMeasurementByDatasetIdBetween(Integer datasetId, Instant from, Instant to) {
        Integer userId = SecurityUtils.userId();
        return measurementRepository.findByDataset_IdAndDataset_Facility_UserIdAndTimestampBetween(datasetId, userId, from, to);
    }

    public void deleteMeasurementById(Integer id) {
        Integer userId = SecurityUtils.userId();
        int deleted = measurementRepository
                .deleteByIdAndDataset_Facility_UserId(id, userId);
        if (deleted == 0) {
            throw new RuntimeException("Measurement not found or not owned by user");
        }
    }
}
