package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.flexdetect.dataservice.model.Dataset;

import java.util.List;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Integer> {
    List<Dataset> findByFacilityId(Integer facilityId);
}
