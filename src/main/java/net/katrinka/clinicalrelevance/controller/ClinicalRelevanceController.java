package net.katrinka.clinicalrelevance.controller;

import net.katrinka.clinicalrelevance.domain.ClinicalRelevance;
import net.katrinka.clinicalrelevance.domain.OperationalRegion;
import net.katrinka.clinicalrelevance.service.ClinicalRelevanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        Iterable<ClinicalRelevance> results = service.findAll();
        List<EntityModel<ClinicalRelevanceResource>> crs = StreamSupport.stream(results.spliterator(), false)
                .map(cr -> new EntityModel<>(new ClinicalRelevanceResource(cr.getAssayCode()),
                        linkTo(methodOn(ClinicalRelevanceController.class).findAll()).withRel("clinicalRelevance")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new CollectionModel<>(crs,
        linkTo(methodOn(ClinicalRelevanceController.class).findAll()).withSelfRel()));
    }

    @GetMapping("/clinicalrelevance/{assayCode}")
    ResponseEntity<EntityModel<ClinicalRelevanceResource>> findByAssayCode(@PathVariable String assayCode,
                                                                                            @RequestParam(required = false) String operationalRegion,
                                                                                            @RequestParam(required = false) String animalCode) {
        logger.info("Request for assay: {}", assayCode);
        logger.info("Operational Region: {}", operationalRegion);
        logger.info("Animal: {}", animalCode);
        Optional<ClinicalRelevance> cr = service.findByAssayCode(assayCode);
        if (cr.isPresent()) {
            logger.info("Found CR for {}", assayCode);
            return ResponseEntity.ok(new EntityModel<>(new ClinicalRelevanceResource(cr.get().getAssayCode())));
        } else {
            logger.warn("No CR found for {}", assayCode);
            return ResponseEntity.notFound().build();
        }


    }

    // TODO: only path variable would be assay code, everything else is a query param since they are all filters
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

    @PostMapping("/clinicalrelevance")
    public void addClinicalRelevance(@RequestBody ClinicalRelevanceResource resource) {
        logger.info("Saving CR: {}", resource.getAssayCode());
        ClinicalRelevance cr = convertFromResource(resource);
        service.createClinicalRelevance(cr);
    }

    @PostMapping("/pubsub")
    public ResponseEntity<String> consumeMessage(@RequestBody Body body) {
        Body.Message message = body.getMessage();
        if (message == null) {
            String errorMessage = "Bad Request: invalid Pub/Sub message format";
            logger.error(errorMessage);
            return ResponseEntity.badRequest().body(errorMessage);
        }
        logger.info("Message ID: {}", message.getMessageId());
        String data = message.getData();
        logger.info("Raw data: {}", data);
        String target = StringUtils.isEmpty(data) ? new String(Base64.getDecoder().decode(data)) : "XXX";
        logger.info("Decoded data: {}", target);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/coolstuff/{message}")
    public String coolStuff(@PathVariable String message) {
        return String.format("Cloud Run this: %s", message);
    }

    private ClinicalRelevance convertFromResource(ClinicalRelevanceResource resource) {
        return new ClinicalRelevance(resource.getAssayCode(), "NOT_IMPLEMENTED", OperationalRegion.CENTRAL_EUROPE);
    }
}
