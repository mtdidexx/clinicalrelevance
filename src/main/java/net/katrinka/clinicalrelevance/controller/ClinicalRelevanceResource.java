package net.katrinka.clinicalrelevance.controller;

public class ClinicalRelevanceResource {
    private String assayCode;

    public ClinicalRelevanceResource() {
    }

    public void setAssayCode(String assayCode) {
        this.assayCode = assayCode;
    }

    public ClinicalRelevanceResource(String assayCode) {
        this.assayCode = assayCode;
    }

    public String getAssayCode() {
        return assayCode;
    }
}
