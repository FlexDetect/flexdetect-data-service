package si.flexdetect.dataservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.flexdetect.dataservice.model.Dataset;
import si.flexdetect.dataservice.model.Facility;
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

    @PostMapping
    public ResponseEntity<Dataset> createDataset(
            @RequestHeader("X-User-Id") Integer userId,
            @PathVariable Integer facilityId,
            @RequestBody Dataset dataset
    ) {
        Facility facility = facilityService.findByIdAndUserId(facilityId, userId)
                .orElseThrow(() -> new RuntimeException("Facility not found or not owned by user"));

        dataset.setFacility(facility);
        Dataset created = datasetService.createDataset(dataset);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Dataset>> getDatasetsByFacility(
            @RequestHeader("X-User-Id") Integer userId,
            @PathVariable Integer facilityId
    ) {
        facilityService.findByIdAndUserId(facilityId, userId)
                .orElseThrow(() -> new RuntimeException("Facility not found or not owned by user"));

        return ResponseEntity.ok(datasetService.getDatasetsByFacilityId(facilityId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dataset> getDatasetById(
            @RequestHeader("X-User-Id") Integer userId,
            @PathVariable Integer facilityId,
            @PathVariable Integer id
    ) {
        facilityService.findByIdAndUserId(facilityId, userId)
                .orElseThrow(() -> new RuntimeException("Facility not found or not owned by user"));

        return datasetService.getDatasetByIdAndFacilityId(id, facilityId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dataset> updateDataset(
            @RequestHeader("X-User-Id") Integer userId,
            @PathVariable Integer facilityId,
            @PathVariable Integer id,
            @RequestBody Dataset updatedDataset) {

        facilityService.findByIdAndUserId(facilityId, userId)
                .orElseThrow(() -> new RuntimeException("Facility not found or not owned by user"));

        Dataset updated = datasetService.updateDataset(id, facilityId, updatedDataset);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDatasetById(
            @RequestHeader("X-User-Id") Integer userId,
            @PathVariable Integer facilityId,
            @PathVariable Integer id
    ) {
        facilityService.findByIdAndUserId(facilityId, userId)
                .orElseThrow(() -> new RuntimeException("Facility not found or not owned by user"));

        datasetService.deleteDatasetByIdAndFacilityId(id, facilityId);
        return ResponseEntity.noContent().build();
    }
}
