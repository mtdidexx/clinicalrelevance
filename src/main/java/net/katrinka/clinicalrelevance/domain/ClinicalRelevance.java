package net.katrinka.clinicalrelevance.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("clinicalrelevance")
public class ClinicalRelevance implements Serializable {
    @Id
    private String assayCode;
    private String animalCode;
    private OperationalRegion operationalRegion;

    public ClinicalRelevance(String assayCode, String animalCode, OperationalRegion operationalRegion) {
        this.assayCode = assayCode;
        this.animalCode = animalCode;
        this.operationalRegion = operationalRegion;
    }

    public String getAssayCode() {
        return assayCode;
    }

    public String getAnimalCode() {
        return animalCode;
    }

    public OperationalRegion getOperationalRegion() {
        return operationalRegion;
    }
}
