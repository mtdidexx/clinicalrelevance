package net.katrinka.clinicalrelevance.controller;

public class ClinicalRelevanceResource {
    private String asayCode;

    public ClinicalRelevanceResource(String asayCode) {
        this.asayCode = asayCode;
    }

    public String getAsayCode() {
        return asayCode;
    }
}
