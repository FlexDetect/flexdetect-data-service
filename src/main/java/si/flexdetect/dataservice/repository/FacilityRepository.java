package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.flexdetect.dataservice.model.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

}
