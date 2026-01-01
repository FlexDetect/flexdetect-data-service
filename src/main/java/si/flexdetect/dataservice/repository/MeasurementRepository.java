package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.flexdetect.dataservice.model.Dataset;
import si.flexdetect.dataservice.model.Measurement;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByDataset_IdAndDataset_Facility_UserId(Integer datasetId, Integer userId);

    Optional<Measurement> findByIdAndDataset_Facility_UserId(Integer id, Integer userId);

    List<Measurement> findByDataset_IdAndDataset_Facility_UserIdAndTimestampBetween(Integer datasetId, Integer userId, Instant from, Instant to);

    int deleteByIdAndDataset_Facility_UserId(Integer id, Integer userId);

    void deleteAllByDataset(Dataset dataset);
}
