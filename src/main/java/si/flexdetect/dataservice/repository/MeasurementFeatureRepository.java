package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.flexdetect.dataservice.model.MeasurementFeature;

import java.util.List;

@Repository
public interface MeasurementFeatureRepository extends JpaRepository<MeasurementFeature, Long> {
    List<MeasurementFeature> findByMeasurementId(Long measurementId);
}
