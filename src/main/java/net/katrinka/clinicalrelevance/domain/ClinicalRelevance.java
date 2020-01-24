package net.katrinka.clinicalrelevance.domain;

public class ClinicalRelevance {
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
