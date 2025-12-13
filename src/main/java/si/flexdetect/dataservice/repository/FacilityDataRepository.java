package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.flexdetect.dataservice.model.Measurement;

public interface FacilityDataRepository extends JpaRepository<Measurement, Long> {

}
