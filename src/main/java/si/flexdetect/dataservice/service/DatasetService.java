package si.flexdetect.dataservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.flexdetect.dataservice.model.Dataset;
import si.flexdetect.dataservice.repository.DatasetRepository;
import si.flexdetect.dataservice.security.SecurityUtils;

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
        Integer userId = SecurityUtils.userId();
        return datasetRepository.findByFacility_IdAndFacility_UserId(facilityId, userId);
    }

    public Optional<Dataset> getDatasetById(Integer id) {
        Integer userId = SecurityUtils.userId();
        return datasetRepository.findByIdAndFacility_UserId(id, userId);
    }

    public Dataset updateDatasetById(Integer id, Dataset updatedDataset) {
        Integer userId = SecurityUtils.userId();
        return datasetRepository.findByIdAndFacility_UserId(id, userId)
                .map(dataset -> {
                    dataset.setCreatedAt(updatedDataset.getCreatedAt());
                    dataset.setSource(updatedDataset.getSource());
                    return datasetRepository.save(dataset);
                })
                .orElseThrow(() ->
                        new RuntimeException("Dataset not found with id " + id + " and userId " + userId)
                );
    }

    public void deleteDatasetById(Integer id) {
        Integer userId = SecurityUtils.userId();
        int deleted = datasetRepository.deleteByIdAndFacility_UserId(id, userId);
        if (deleted == 0) {
            throw new RuntimeException(
                    "Dataset not found with id " + id + " and userId " + userId
            );
        }
    }
}
