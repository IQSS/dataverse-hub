package edu.harvard.iq.dataverse_hub.scheduled;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import edu.harvard.iq.dataverse_hub.controller.scheduled.DevMetricsImporter;

@SpringBootTest
public class DevMetricsImporterTests {

    @Autowired
    DevMetricsImporter devmetricsimporter;

    @Test
    public void testRunTask() {

        assertDoesNotThrow(() -> {
            assertNotNull(devmetricsimporter.startTask(true));
        });

        assertDoesNotThrow(() -> {
            assertNull(devmetricsimporter.startTask(false));
        });
        
        assertDoesNotThrow(() -> {
            devmetricsimporter.importDevMetrics();
        });
        
    }

}
