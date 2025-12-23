package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.flexdetect.dataservice.model.MeasurementName;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementNameRepository extends JpaRepository<MeasurementName, Integer> {
    List<MeasurementName> findByUserId(Integer userId);

    int deleteByIdAndUserId(Integer id, Integer userId);

    Optional<MeasurementName> findByIdAndUserId(Integer id, Integer userId);
}
