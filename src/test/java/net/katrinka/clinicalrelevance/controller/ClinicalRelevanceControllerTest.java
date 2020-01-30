package net.katrinka.clinicalrelevance.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClinicalRelevanceControllerTest {
    private ClinicalRelevanceController controller;

    @BeforeEach
    void setUp() {
        controller = new ClinicalRelevanceController(null);
    }

    @AfterEach
    void tearDown() {
        controller = null;
    }

    @Test
    void findAll() {
    }

    @Test
    void findByAssayCode() {
    }

    @Test
    void findByAssayAndRegion() {
    }
}