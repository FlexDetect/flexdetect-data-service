package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.flexdetect.dataservice.model.FacilityData;

public interface FacilityDataRepository extends JpaRepository<FacilityData, Long> {

}
