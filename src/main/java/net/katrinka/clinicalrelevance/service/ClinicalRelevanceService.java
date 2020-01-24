package net.katrinka.clinicalrelevance.service;

import net.katrinka.clinicalrelevance.domain.ClinicalRelevance;
import net.katrinka.clinicalrelevance.domain.OperationalRegion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicalRelevanceService {
    private static Logger log = LoggerFactory.getLogger(ClinicalRelevance.class);
    private List<ClinicalRelevance> crs;

    public ClinicalRelevanceService() {
        crs = new ArrayList<>();
        crs.add(new ClinicalRelevance("ALB", "CANINE", OperationalRegion.CENTRAL_EUROPE));
        crs.add(new ClinicalRelevance("WBC", "CANINE", OperationalRegion.CENTRAL_EUROPE));
    }

    public List<ClinicalRelevance> findAll() {
        return crs;
    }

    public List<ClinicalRelevance> findByAssayCode(String assayCode) {
        log.info("Looking for assay: {}", assayCode);
        return crs.stream().filter(cr -> cr.getAssayCode().equalsIgnoreCase(assayCode)).collect(Collectors.toList());
    }
}
