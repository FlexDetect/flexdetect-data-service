package si.flexdetect.dataservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.flexdetect.dataservice.model.Dataset;
import si.flexdetect.dataservice.repository.DatasetRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DatasetService {

    private final DatasetRepository datasetRepository;

    public DatasetService(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }

    public Dataset createDataset(Dataset dataset) {
        return datasetRepository.save(dataset);
    }

    public List<Dataset> getDatasetsByFacilityId(Integer facilityId) {
        return datasetRepository.findByFacilityId(facilityId);
    }

    public Optional<Dataset> getDatasetByIdAndFacilityId(Integer id, Integer facilityId) {
        return datasetRepository.findByIdAndFacilityId(id, facilityId);
    }

    public Dataset updateDataset(Integer id, Integer facilityId, Dataset updatedDataset) {
        return datasetRepository.findByIdAndFacilityId(id, facilityId)
                .map(dataset -> {
                    dataset.setCreatedAt(updatedDataset.getCreatedAt());
                    dataset.setSource(updatedDataset.getSource());
                    return datasetRepository.save(dataset);
                })
                .orElseThrow(() ->
                        new RuntimeException("Dataset not found with id " + id + " and facilityId " + facilityId)
                );
    }

    public void deleteDatasetByIdAndFacilityId(Integer id, Integer facilityId) {
        int deleted = datasetRepository.deleteByIdAndFacilityId(id, facilityId);
        if (deleted == 0) {
            throw new RuntimeException(
                    "Dataset not found with id " + id + " and facilityId " + facilityId
            );
        }
    }
}
