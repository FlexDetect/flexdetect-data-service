package si.flexdetect.dataservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.flexdetect.dataservice.dto.MeasurementBulkCreateRequest;
import si.flexdetect.dataservice.dto.MeasurementCreateRequest;
import si.flexdetect.dataservice.model.Measurement;
import si.flexdetect.dataservice.security.SecurityUtils;
import si.flexdetect.dataservice.service.MeasurementService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/datasets/{datasetId}/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public Measurement create(@PathVariable Integer datasetId, @RequestBody MeasurementCreateRequest req) {
        System.out.println("test");
        return measurementService.createMeasurement(req, datasetId);
    }
    @PostMapping("/bulk")
    public ResponseEntity<Void> bulkInsert(@PathVariable Integer datasetId, @RequestBody MeasurementBulkCreateRequest request) {
        measurementService.bulkInsert(datasetId, request);
        return ResponseEntity.accepted().build();
    }


    @GetMapping
    public List<Measurement> getAll(@PathVariable Integer datasetId) {
        return measurementService.getMeasurementByDatasetId(datasetId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Measurement> getById(@PathVariable Integer datasetId, @PathVariable Integer id) {
        return measurementService.getMeasurementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/range")
    public List<Measurement> getByTimeRange(@PathVariable Integer datasetId, @RequestParam Instant from, @RequestParam Instant to) {
        return measurementService.getMeasurementByDatasetIdBetween(datasetId, from, to);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        measurementService.deleteMeasurementById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAllForDataset(@PathVariable Integer datasetId) {
        measurementService.deleteAllByDatasetId(datasetId);
        return ResponseEntity.noContent().build();
    }

}
