package edu.harvard.iq.dataverse_hub.scheduled;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.harvard.iq.dataverse_hub.controller.scheduled.InstallationMetricsImporter;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationMetrics;
import edu.harvard.iq.dataverse_hub.service.InstallationService;

@SpringBootTest
public class InstallationMetricsImporterTests {

    @Autowired
    InstallationMetricsImporter installationMetricsImporter;

    @Autowired
    InstallationService installationService;

    @Test
    public void testImportMetrics() {
        ArrayList<Installation> installationList = new ArrayList<Installation>();

        Installation installation = installationService.findAll().get(0); 
        installationList.add(installation);

        Installation invalidInstallation = installationService.findAll().get(1); 
        invalidInstallation.setHostname("invalid.url.ex");  
        installationList.add(invalidInstallation);

        assertDoesNotThrow(() -> {
            List<InstallationMetrics> metrics =  installationMetricsImporter.importInstallationsMetrics(installationList);
            assertNotNull(metrics.toString());
            metrics.get(0).setRecordId(null);
        });

    }

}
