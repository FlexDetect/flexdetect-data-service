package si.flexdetect.dataservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.flexdetect.dataservice.model.MeasurementName;
import si.flexdetect.dataservice.security.SecurityUtils;
import si.flexdetect.dataservice.service.MeasurementNameService;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/measurement-names")
public class MeasurementNameController {

    private final MeasurementNameService measurementNameService;

    public MeasurementNameController(MeasurementNameService measurementNameService) {
        this.measurementNameService = measurementNameService;
    }

    @GetMapping
    public List<MeasurementName> getAll() {
        Integer userId = SecurityUtils.userId();
        return measurementNameService.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasurementName> getById(@PathVariable Integer id) {
        Integer userId = SecurityUtils.userId();
        return measurementNameService.findByIdAndUserId(id, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MeasurementName> create(@RequestBody MeasurementName measurementName) {
        Integer userId = SecurityUtils.userId();
        measurementName.setUserId(userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(measurementNameService.createMeasurementName(measurementName));
    }

    @PutMapping("/{id}")
    public MeasurementName update(@PathVariable Integer id, @RequestBody MeasurementName measurementName) {
        Integer userId = SecurityUtils.userId();
        return measurementNameService.updateMeasurementName(id, userId, measurementName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws AccessDeniedException {
        Integer userId = SecurityUtils.userId();
        measurementNameService.deleteMeasurementName(id, userId);
        return ResponseEntity.noContent().build();
    }
}
