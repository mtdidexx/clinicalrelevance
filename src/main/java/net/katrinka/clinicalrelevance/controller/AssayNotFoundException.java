package net.katrinka.clinicalrelevance.controller;

public class AssayNotFoundException extends RuntimeException {
    public AssayNotFoundException(String assayCode) {
        super("Could not find Assay: " + assayCode);
    }
}
