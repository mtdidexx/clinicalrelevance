package net.katrinka.clinicalrelevance.controller;

import net.katrinka.clinicalrelevance.service.ClinicalRelevanceService;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.ByteBuffer;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClinicalRelevanceController.class)
public class CRControllerSpringTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ClinicalRelevanceService clinicalRelevanceService;

    @Test
    public void coolStuff() throws Exception {
        String message = "Testaroo";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/coolstuff/%s", message))).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.SC_OK, status);
        assertEquals(String.format("Cloud Run this: %s", message), result.getResponse().getContentAsString());
    }

    @Test
    public void consumeFromPubSub() throws Exception {
        JSONObject message = new JSONObject().put("messageId", "1234").put("data", Base64.getEncoder().encodeToString("{\"assayCode\": \"ALB\"}".getBytes()));
        JSONObject pubsubMessage = new JSONObject().put("message", message);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pubsub")
        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(pubsubMessage.toString()))
                .andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.SC_BAD_REQUEST, status);
    }
}
