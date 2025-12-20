package si.flexdetect.dataservice.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.flexdetect.dataservice.model.Measurement;
import si.flexdetect.dataservice.repository.MeasurementRepository;

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

    public

}
