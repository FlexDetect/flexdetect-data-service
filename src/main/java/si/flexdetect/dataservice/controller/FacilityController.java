package si.flexdetect.dataservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.flexdetect.dataservice.model.Facility;
import si.flexdetect.dataservice.service.FacilityService;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping
    public List<Facility> getAll(@RequestHeader("X-User-Id") Integer userId) {
        return facilityService.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facility> getById(@PathVariable Integer id, @RequestHeader("X-User-Id") Integer userId) {
        return facilityService.findByIdAndUserId(id, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Facility> create(@RequestHeader("X-User-Id") Integer userId, @RequestBody Facility facility) {
        facility.setUserId(userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(facilityService.createFacility(facility));
    }

    @PutMapping("/{id}")
    public Facility update(@PathVariable Integer id, @RequestHeader("X-User-Id") Integer userId, @RequestBody Facility facility) {
        return facilityService.updateFacility(id, userId, facility);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, @RequestHeader("X-User-Id") Integer userId) throws AccessDeniedException {
        facilityService.deleteFacility(id, userId);
        return ResponseEntity.noContent().build();
    }
}
