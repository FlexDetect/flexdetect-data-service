package si.flexdetect.dataservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import si.flexdetect.dataservice.model.Dataset;
import si.flexdetect.dataservice.model.Facility;
import si.flexdetect.dataservice.security.SecurityUtils;
import si.flexdetect.dataservice.service.DatasetService;
import si.flexdetect.dataservice.service.FacilityService;

import java.util.List;

@RestController
@RequestMapping("/api/facilities/{facilityId}/datasets")
public class DatasetController {

    private final DatasetService datasetService;
    private final FacilityService facilityService;

    public DatasetController(DatasetService datasetService, FacilityService facilityService) {
        this.datasetService = datasetService;
        this.facilityService = facilityService;
    }
    private void verifyFacilityOwnership(Integer facilityId, Integer userId) {
        facilityService.findByIdAndUserId(facilityId, userId)
                .orElseThrow(() -> new AccessDeniedException("Facility not found or not owned by user"));
    }
    @PostMapping
    public ResponseEntity<Dataset> createDataset(@PathVariable Integer facilityId, @RequestBody Dataset dataset) {
        Integer userId = SecurityUtils.userId();
        // Sledeča vrstica je namenjena preverjanju avtorizacije, ki je potrebna poleg avtitentifikacije.
        Facility facility = facilityService.findByIdAndUserId(facilityId, userId)
                .orElseThrow(() -> new AccessDeniedException("Facility not found or not owned by user"));
        dataset.setFacility(facility);
        Dataset created = datasetService.createDataset(dataset);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Dataset>> getDatasetsByFacility(@PathVariable Integer facilityId) {
        Integer userId = SecurityUtils.userId();
        verifyFacilityOwnership(facilityId, userId);
        return ResponseEntity.ok(datasetService.getDatasetsByFacilityId(facilityId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dataset> getDatasetById(@PathVariable Integer facilityId, @PathVariable Integer id) {
        Integer userId = SecurityUtils.userId();
        verifyFacilityOwnership(facilityId, userId);
        return datasetService.getDatasetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dataset> updateDataset(@PathVariable Integer facilityId, @PathVariable Integer id, @RequestBody Dataset updatedDataset) {
        Integer userId = SecurityUtils.userId();
        verifyFacilityOwnership(facilityId, userId);
        Dataset updated = datasetService.updateDatasetById(id, updatedDataset);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDatasetById(@PathVariable Integer facilityId, @PathVariable Integer id) {
        Integer userId = SecurityUtils.userId();
        verifyFacilityOwnership(facilityId, userId);
        datasetService.deleteDatasetById(id);
        return ResponseEntity.noContent().build();
    }
}
