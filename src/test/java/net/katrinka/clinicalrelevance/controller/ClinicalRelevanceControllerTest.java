package net.katrinka.clinicalrelevance.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClinicalRelevanceControllerTest {
    @Test
    public void coolStuffSuccess() {
        String response = given().standaloneSetup(new ClinicalRelevanceController(null))
        .when().get("/coolstuff/Testaroo")
        .then().status(HttpStatus.OK).extract().body().asString();
        assertEquals("Cloud Run this: Testaroo", response);
    }
}