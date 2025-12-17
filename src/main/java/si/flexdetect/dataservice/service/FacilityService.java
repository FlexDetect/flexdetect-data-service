package si.flexdetect.dataservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.flexdetect.dataservice.model.Facility;
import si.flexdetect.dataservice.repository.FacilityRepository;

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

    public Optional<Facility> getFacilityById(Integer id) {
        return facilityRepository.findById(id);
    }

    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    public Facility updateFacility(Integer id, Facility updatedFacility) {
        return facilityRepository.findById(id).map(facility -> {
            facility.setName(updatedFacility.getName());
            facility.setAddress(updatedFacility.getAddress());
            facility.setType(updatedFacility.getType());
            facility.setSize(updatedFacility.getSize());
            facility.setFloors(updatedFacility.getFloors());
            facility.setContactName(updatedFacility.getContactName());
            facility.setContactPhone(updatedFacility.getContactPhone());
            facility.setContactEmail(updatedFacility.getContactEmail());
            return facilityRepository.save(facility);
        }).orElseThrow(() -> new RuntimeException("Facility not found with id " + id));
    }

    public void deleteFacility(Integer id) {
        facilityRepository.deleteById(id);
    }
}
