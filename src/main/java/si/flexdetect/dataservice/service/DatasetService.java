package si.flexdetect.dataservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.flexdetect.dataservice.model.Dataset;
import si.flexdetect.dataservice.repository.DatasetRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DatasetService {

    private DatasetRepository datasetRepository;

    public DatasetService(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }

    public Dataset createDataset(Dataset dataset) {
        return datasetRepository.save(dataset);
    }

    public Optional<Dataset> getFacilityById(Integer id) {
        return datasetRepository.findById(id);
    }

    public List<Dataset> getAllDatasets() {
        return datasetRepository.findAll();
    }

    public Dataset updateDataset(Integer id, Dataset updatedDataset) {
        return datasetRepository.findById(id).map(dataset -> {
            dataset.setCreatedAt(updatedDataset.getCreatedAt());
            dataset.setSource(updatedDataset.getSource());
            return datasetRepository.save(dataset);
        }).orElseThrow(() -> new RuntimeException("Dataset not found with id " + id));
    }


    public List<Dataset> getDatasetsByFacilityId(Integer facilityId) {
        return datasetRepository.findByFacilityId(facilityId);
    }

    public Optional<Dataset> getDatasetByIdAndFacilityId(Integer id, Integer facilityId) {
        return getDatasetsByFacilityId(facilityId).stream()
                .filter(dataset -> dataset.getId().equals(id))
                .findFirst();
    }

    public ResponseEntity<Void> deleteDatasetByIdAndFacilityId(Integer id, Integer facilityId) {
            Optional<Dataset> datasetOpt = getDatasetsByFacilityId(facilityId).stream()
                    .filter(d -> d.getId().equals(id))
                    .findFirst();

            if (datasetOpt.isPresent()) {
                datasetRepository.delete(datasetOpt.get());
            } else {
                throw new RuntimeException("Dataset not found with id " + id + " and facilityId " + facilityId);
            }
            return ResponseEntity.noContent().build();
    }

}
