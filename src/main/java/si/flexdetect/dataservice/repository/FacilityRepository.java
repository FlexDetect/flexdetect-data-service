package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.flexdetect.dataservice.model.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

}
