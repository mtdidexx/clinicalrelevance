package net.katrinka.clinicalrelevance.service;

import net.katrinka.clinicalrelevance.domain.ClinicalRelevance;
import net.katrinka.clinicalrelevance.repository.ClinicalRelevanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClinicalRelevanceService {
    private static Logger log = LoggerFactory.getLogger(ClinicalRelevance.class);
    private ClinicalRelevanceRepository repository;

    public ClinicalRelevanceService(ClinicalRelevanceRepository repository) {
        this.repository = repository;
    }

    public Iterable<ClinicalRelevance> findAll() {
        return repository.findAll();
    }

    public Optional<ClinicalRelevance> findByAssayCode(String assayCode) {
        log.info("Looking for assay: {}", assayCode);
        return repository.findById(assayCode);
    }

    public void createClinicalRelevance(ClinicalRelevance clinicalRelevance) {
        repository.save(clinicalRelevance);
    }
}
