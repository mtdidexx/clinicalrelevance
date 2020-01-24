package net.katrinka.clinicalrelevance.controller;

import net.katrinka.clinicalrelevance.domain.ClinicalRelevance;
import net.katrinka.clinicalrelevance.service.ClinicalRelevanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ClinicalRelevanceController {
    private static Logger logger = LoggerFactory.getLogger(ClinicalRelevanceController.class);
    private ClinicalRelevanceService service;

    public ClinicalRelevanceController(ClinicalRelevanceService service) {
        this.service = service;
    }

    @GetMapping("/clinicalrelevance")
    ResponseEntity<CollectionModel<EntityModel<ClinicalRelevanceResource>>> findAll() {
        logger.info("Finding all CR data");
        List<EntityModel<ClinicalRelevanceResource>> crs = service.findAll().stream()
                .map(cr -> new EntityModel<>(new ClinicalRelevanceResource(cr.getAssayCode()),
                        linkTo(methodOn(ClinicalRelevanceController.class).findAll()).withRel("clinicalRelevance")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new CollectionModel<>(crs,
        linkTo(methodOn(ClinicalRelevanceController.class).findAll()).withSelfRel()));
    }

    @GetMapping("/clinicalrelevance/{assayCode}")
    ResponseEntity<CollectionModel<EntityModel<ClinicalRelevanceResource>>> findByAssayCode(@PathVariable String assayCode,
                                                                                            @RequestParam(required = false) String operationalRegion,
                                                                                            @RequestParam(required = false) String animalCode) {
        logger.info("Request for assay: {}", assayCode);
        logger.info("Operational Region: {}", operationalRegion);
        logger.info("Animal: {}", animalCode);
        List<ClinicalRelevance> results = service.findByAssayCode(assayCode);
        logger.info("Found {} CRs", results.size());

        List<EntityModel<ClinicalRelevanceResource>> resources = results.stream()
                .map(cr -> new EntityModel<>(new ClinicalRelevanceResource(cr.getAssayCode())))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new CollectionModel<>(resources));
    }

    @GetMapping("/assay/{assayCode}/region/{regionCode}/animal/{animalCode}/sex/{sexCode}/age/{age}/sample/{sampleCode}")
    public EntityModel<ClinicalRelevanceResource> findByAssayAndRegion(@PathVariable String assayCode,
                                                                       @PathVariable String regionCode,
                                                                       @PathVariable String animalCode,
                                                                       @PathVariable String sexCode,
                                                                       @PathVariable String age,
                                                                       @PathVariable String sampleCode) {
        logger.info("assay: {} | region: {}", assayCode, regionCode);
        logger.info("Animal code: {} | sex: {} | age: {}", animalCode, sexCode, age);
        logger.info("Sample: {}", sampleCode);
        return new EntityModel<>(new ClinicalRelevanceResource(assayCode));
    }
}
