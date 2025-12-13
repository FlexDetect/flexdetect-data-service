package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.flexdetect.dataservice.model.MeasurementFeature;

public interface MeasurementFeatureRepository extends JpaRepository<MeasurementFeature, Long> {

}
