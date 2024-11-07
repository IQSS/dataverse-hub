package edu.harvard.iq.dataverse_hub.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.harvard.iq.dataverse_hub.controller.api.ErrorResponseController;

@SpringBootTest
public class ErrorResponseControllerTests {

    @Autowired
    private ErrorResponseController errorResponseController;

    @Test
    public void testErrorResponse() {

        errorResponseController.errorResponse(new Exception("Test"));
        errorResponseController.badRequestExceptionResponse(new Exception());
        errorResponseController.noResourceFoundExceptionResponse(new Exception());
    

    }

}
