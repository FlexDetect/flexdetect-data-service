package si.flexdetect.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.flexdetect.dataservice.model.Facility;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {
    List<Facility> findByUserId(Integer userId);

    int deleteByIdAndUserId(Integer id, Integer userId);

    Optional<Facility> findByIdAndUserId(Integer id, Integer userId);
}
