package si.flexdetect.dataservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.flexdetect.dataservice.model.Facility;
import si.flexdetect.dataservice.repository.FacilityRepository;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public Facility createFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    public List<Facility> findByUserId(Integer userId) {
        return facilityRepository.findByUserId(userId);
    }

    public Optional<Facility> findByIdAndUserId(Integer id, Integer userId) {
        return facilityRepository.findByIdAndUserId(id, userId);
    }

    public void deleteFacility(Integer id, Integer userId) throws AccessDeniedException {
        int deleted = facilityRepository.deleteByIdAndUserId(id, userId);
        if (deleted == 0) {
            throw new AccessDeniedException("Facility not found or not yours.");
        }
    }

    public Facility updateFacility(Integer id, Integer userId, Facility updatedFacility) {
        return facilityRepository.findByIdAndUserId(id, userId).map(facility -> {
            facility.setName(updatedFacility.getName());
            facility.setAddress(updatedFacility.getAddress());
            facility.setType(updatedFacility.getType());
            facility.setSize(updatedFacility.getSize());
            facility.setFloors(updatedFacility.getFloors());
            facility.setContactName(updatedFacility.getContactName());
            facility.setContactPhone(updatedFacility.getContactPhone());
            facility.setContactEmail(updatedFacility.getContactEmail());
            return facilityRepository.save(facility);
        }).orElseThrow(() -> new RuntimeException("Facility not found or not owned by user"));
    }
}
