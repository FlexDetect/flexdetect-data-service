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
    private DatasetService datasetService;
    private FacilityService facilityService;

    public DatasetController(DatasetService datasetService, FacilityService facilityService) {
        this.datasetService = datasetService;
        this.facilityService = facilityService;
    }

    @PostMapping
    public ResponseEntity<Dataset> createDataset(@PathVariable Integer facilityId, @RequestBody Dataset dataset) {
        Facility facility = facilityService.getFacilityById(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found"));
        dataset.setFacility(facility);
        Dataset created =  datasetService.createDataset(dataset);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Dataset>> getDatasetsByFacility(@PathVariable Integer facilityId) {
        return ResponseEntity.ok(datasetService.getDatasetsByFacilityId(facilityId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Dataset> getDatasetById(@PathVariable Integer facilityId, @PathVariable Integer id) {
        return datasetService.getDatasetByIdAndFacilityId(id, facilityId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDatasetById(@PathVariable Integer facilityId, @PathVariable Integer id) {
        datasetService.deleteDatasetByIdAndFacilityId(id, facilityId);
        return ResponseEntity.noContent().build();
    }
}
