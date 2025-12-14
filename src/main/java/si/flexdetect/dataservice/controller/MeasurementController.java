package si.flexdetect.dataservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.flexdetect.dataservice.model.Measurement;
import si.flexdetect.dataservice.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public ResponseEntity<Measurement> createMeasurement(@RequestBody Measurement measurement) {
        Measurement created = measurementService.createMeasurement(measurement);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Measurement> getMeasurementById(@PathVariable Long id) {
        return measurementService.getMeasurementById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Measurement>> getAllMeasurements() {
        return ResponseEntity.ok(measurementService.getAllMeasurements());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Measurement> updateMeasurement(@PathVariable Long id, @RequestBody Measurement measurement) {
        try {
            Measurement updated = measurementService.updateMeasurement(id, measurement);
        return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeasurement(@PathVariable Long id) {
            measurementService.deleteMeasurement(id);
            return ResponseEntity.ok().build();
    }
}
