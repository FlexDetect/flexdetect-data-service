package si.flexdetect.dataservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.flexdetect.dataservice.model.MeasurementFeature;
import si.flexdetect.dataservice.service.MeasurementFeatureService;

import java.util.List;
@RestController
@RequestMapping("/api/measurements/{measurementId}/features")
public class MeasurementFeatureController {

    private final MeasurementFeatureService measurementFeatureService;

    public MeasurementFeatureController(MeasurementFeatureService measurementFeatureService) {
        this.measurementFeatureService = measurementFeatureService;
    }

    @PostMapping
    public ResponseEntity<MeasurementFeature> createMeasurementFeature(@PathVariable Long measurementId,
                                                                       @RequestBody MeasurementFeature feature) {
        MeasurementFeature created = measurementFeatureService.createMeasurementFeature(feature);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasurementFeature> getMeasurementFeatureById(@PathVariable Long id) {
        return measurementFeatureService.getMeasurementFeatureById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MeasurementFeature>> getFeaturesForMeasurement(@PathVariable Long measurementId) {
        return ResponseEntity.ok(measurementFeatureService.getFeaturesForMeasurement(measurementId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeasurementFeature> updateMeasurementFeature(@PathVariable Long id, @RequestBody MeasurementFeature feature) {
        try {
            MeasurementFeature updated = measurementFeatureService.updateMeasurementFeature(id, feature);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeasurementFeature(@PathVariable Long id) {
        measurementFeatureService.deleteMeasurementFeatureById(id);
        return ResponseEntity.noContent().build();
    }
}
