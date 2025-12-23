package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.flexdetect.dataservice.model.Dataset;
import si.flexdetect.dataservice.model.Facility;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Integer> {
    List<Dataset> findByFacility_IdAndFacility_UserId(Integer facilityId, Integer userId);

    int deleteByIdAndFacility_UserId(Integer id, Integer userId);

    Optional<Dataset> findByIdAndFacility_UserId(Integer id, Integer userId);

    boolean existsByIdAndFacility_UserId(Integer id, Integer userId);
}
