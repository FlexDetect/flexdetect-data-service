package si.flexdetect.dataservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.flexdetect.dataservice.model.MeasurementName;
import si.flexdetect.dataservice.repository.MeasurementNameRepository;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MeasurementNameService {

    private final MeasurementNameRepository measurementNameRepository;

    public MeasurementNameService(MeasurementNameRepository measurementNameRepository) {
        this.measurementNameRepository = measurementNameRepository;
    }

    public MeasurementName createMeasurementName(MeasurementName measurementName) {
        return measurementNameRepository.save(measurementName);
    }

    public List<MeasurementName> findByUserId(Integer userId) {
        return measurementNameRepository.findByUserId(userId);
    }

    public Optional<MeasurementName> findByIdAndUserId(Integer id, Integer userId) {
        return measurementNameRepository.findByIdAndUserId(id, userId);
    }

    public void deleteMeasurementName(Integer id, Integer userId) throws AccessDeniedException {
        int deleted = measurementNameRepository.deleteByIdAndUserId(id, userId);
        if (deleted == 0) {
            throw new AccessDeniedException("MeasurementName not found or not yours.");
        }
    }

    public MeasurementName updateMeasurementName(Integer id, Integer userId, MeasurementName updatedMeasurementName) {
        return measurementNameRepository.findByIdAndUserId(id, userId).map(measurementName -> {
            measurementName.setName(updatedMeasurementName.getName());
            measurementName.setUnit(updatedMeasurementName.getUnit());
            measurementName.setDataType(updatedMeasurementName.getDataType());
            return measurementNameRepository.save(measurementName);
        }).orElseThrow(() -> new RuntimeException("MeasurementName not found or not owned by user."));
    }
}
