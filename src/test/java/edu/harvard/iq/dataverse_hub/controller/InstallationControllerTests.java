package edu.harvard.iq.dataverse_hub.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.harvard.iq.dataverse_hub.controller.api.InstallationController;
import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationFilterParams;

@SpringBootTest
public class InstallationControllerTests {

    @Autowired
    private InstallationController installationController;

    @Test
    public void testInstallationController() {

        assertDoesNotThrow(() -> {
            installationController.getInstallations(new InstallationFilterParams());
            installationController.geInstallationsStatus();
        });
    }

    

}
