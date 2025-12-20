package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.flexdetect.dataservice.model.Measurement;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    Optional<Measurement> findByIdAndDataset_IdAndDataset_Facility_UserId(Integer measurementId, Integer datasetId, Integer userId);

    List<Measurement> findByDataset_IdAndDataset_Facility_UserId(Integer datasetId, Integer userId);

    int deleteByIdAndDataset_IdAndDataset_Facility_UserId(Integer measurementId, Integer datasetId, Integer userId);

    List<Measurement> findByDataset_Facility_IdAndDataset_Facility_UserId(Integer facilityId, Integer userId);
}
