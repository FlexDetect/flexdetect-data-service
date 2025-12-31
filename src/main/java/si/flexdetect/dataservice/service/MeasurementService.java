package si.flexdetect.dataservice.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.flexdetect.dataservice.dto.MeasurementCreateRequest;
import si.flexdetect.dataservice.model.Dataset;
import si.flexdetect.dataservice.model.Measurement;
import si.flexdetect.dataservice.model.MeasurementName;
import si.flexdetect.dataservice.repository.DatasetRepository;
import si.flexdetect.dataservice.repository.MeasurementNameRepository;
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
    private final MeasurementNameRepository measurementNameRepository;

    public MeasurementService(MeasurementRepository measurementRepository, DatasetRepository datasetRepository, MeasurementNameRepository measurementNameRepository) {
        this.measurementRepository = measurementRepository;
        this.datasetRepository = datasetRepository;
        this.measurementNameRepository = measurementNameRepository;
    }

    public Measurement createMeasurement(MeasurementCreateRequest req, Integer datasetId) {
        Integer userId = SecurityUtils.userId();

        Dataset dataset = datasetRepository
                .findByIdAndFacility_UserId(datasetId, userId)
                .orElseThrow(() -> new AccessDeniedException("Dataset not owned by user"));

        MeasurementName measurementName =
                measurementNameRepository
                        .findByIdAndUserId(req.getMeasurementNameId(), userId)
                        .orElseThrow(() -> new AccessDeniedException("MeasurementName not owned by user"));

        Measurement m = new Measurement();
        m.setDataset(dataset);
        m.setMeasurementNameIdMeasurementName(measurementName);
        m.setTimestamp(req.getTimestamp());

        m.setValueInt(req.getValueInt());
        m.setValueFloat(req.getValueFloat());
        m.setValueBool(req.getValueBool());

        return measurementRepository.save(m);
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
