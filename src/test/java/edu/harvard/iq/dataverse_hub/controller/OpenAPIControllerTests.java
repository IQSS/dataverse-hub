package edu.harvard.iq.dataverse_hub.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.harvard.iq.dataverse_hub.controller.api.OpenAPIController;

@SpringBootTest
public class OpenAPIControllerTests {

    @Test   
    public void testOpenAPIController() {
        assertDoesNotThrow(() -> {
            OpenAPIController openAPIController = new OpenAPIController();
            openAPIController.getClass();
        });
    }

}
