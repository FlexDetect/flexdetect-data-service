package si.flexdetect.dataservice.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.flexdetect.dataservice.dto.MeasurementCreateRequest;
import si.flexdetect.dataservice.dto.MeasurementBulkCreateRequest;
import si.flexdetect.dataservice.model.Dataset;
import si.flexdetect.dataservice.model.Measurement;
import si.flexdetect.dataservice.model.MeasurementName;
import si.flexdetect.dataservice.repository.DatasetRepository;
import si.flexdetect.dataservice.repository.MeasurementNameRepository;
import si.flexdetect.dataservice.repository.MeasurementRepository;
import si.flexdetect.dataservice.security.SecurityUtils;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final DatasetRepository datasetRepository;
    private final MeasurementNameRepository measurementNameRepository;
    private final JdbcTemplate jdbcTemplate;

    public MeasurementService(MeasurementRepository measurementRepository, DatasetRepository datasetRepository, MeasurementNameRepository measurementNameRepository, JdbcTemplate jdbcTemplate) {
        this.measurementRepository = measurementRepository;
        this.datasetRepository = datasetRepository;
        this.measurementNameRepository = measurementNameRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void bulkInsert(Integer datasetId, MeasurementBulkCreateRequest req) {
        Integer userId = SecurityUtils.userId();
        Dataset dataset = datasetRepository
                .findByIdAndFacility_UserId(datasetId, userId)
                .orElseThrow(() -> new AccessDeniedException("Dataset not owned by user"));

        List<MeasurementBulkCreateRequest.Row> rows = req.getRows();
        if (rows == null || rows.isEmpty()) {
            return;
        }

        // Collect unique measurementNameIds
        Set<Integer> nameIds = rows.stream()
                .map(MeasurementBulkCreateRequest.Row::getMeasurementNameId)
                .collect(Collectors.toSet());


        Map<Integer, MeasurementName> allowedNames =
                measurementNameRepository
                        .findAllByIdInAndUserId(new ArrayList<>(nameIds), userId)
                        .stream()
                        .collect(Collectors.toMap(MeasurementName::getId, m -> m));

        if (allowedNames.size() != nameIds.size()) {
            throw new AccessDeniedException("One or more measurement names not owned by user");
        }

        // Validate exactly one value is set
        for (var r : rows) {
            int count =
                    (r.getValueInt() != null ? 1 : 0) +
                            (r.getValueFloat() != null ? 1 : 0) +
                            (r.getValueBool() != null ? 1 : 0);

            if (count != 1) {
                throw new IllegalArgumentException("Exactly one value field must be set");
            }
        }

        String sql = """
            INSERT INTO measurement (
                dataset_id_dataset,
                measurement_name_id_measurement_name,
                timestamp,
                value_int,
                value_float,
                value_bool
            ) VALUES (?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.batchUpdate(
                sql,
                rows,
                1000,
                (ps, r) -> {
                    ps.setInt(1, dataset.getId());
                    ps.setInt(2, r.getMeasurementNameId());
                    ps.setTimestamp(3, Timestamp.from(r.getTimestamp()));

                    ps.setObject(4, r.getValueInt(), Types.INTEGER);
                    ps.setObject(5, r.getValueFloat(), Types.DOUBLE);
                    ps.setObject(6, r.getValueBool(), Types.BOOLEAN);
                }
        );
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
    @Transactional
    public void deleteAllByDatasetId(Integer datasetId) {
        Integer userId = SecurityUtils.userId();

        Dataset dataset = datasetRepository
                .findByIdAndFacility_UserId(datasetId, userId)
                .orElseThrow(() -> new AccessDeniedException("Dataset not owned by user"));

        measurementRepository.deleteAllByDataset(dataset);
    }

}
