package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.flexdetect.dataservice.model.MeasurementName;

@Repository
public interface MeasurementNameRepository extends JpaRepository<MeasurementName, Integer> {

}
