package si.flexdetect.dataservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.flexdetect.dataservice.model.MeasurementName;
import si.flexdetect.dataservice.service.MeasurementNameService;

import java.util.List;

@RestController
@RequestMapping("/api/measurement-names")
public class MeasurementNameController {

    private final MeasurementNameService measurementNameService;

    public MeasurementNameController(MeasurementNameService measurementNameService) {
        this.measurementNameService = measurementNameService;
    }

    @PostMapping
    public ResponseEntity<MeasurementName> createMeasurementName(@RequestBody MeasurementName measurementName) {
        MeasurementName created = measurementNameService.createMeasurementName(measurementName);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasurementName> getMeasurementNameById(@PathVariable Integer id) {
        return measurementNameService.getMeasurementNameById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
    @GetMapping
    public ResponseEntity<List<MeasurementName>> getAllMeasurementNames() {
        return ResponseEntity.ok(measurementNameService.getAllMeasurementNames());
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<MeasurementName> updateMeasurementName(@PathVariable Integer id,
                                                                 @RequestBody MeasurementName measurementName) {
        try {
            MeasurementName updated = measurementNameService.updateMeasurementName(id, measurementName);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeasurementName(@PathVariable Integer id) {
        measurementNameService.deleteMeasurementName(id);
        return ResponseEntity.noContent().build();
    }
}
