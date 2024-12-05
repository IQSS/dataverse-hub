package edu.harvard.iq.dataverse_hub.service;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import edu.harvard.iq.dataverse_hub.controller.scheduled.InstallationGitImporter;
import edu.harvard.iq.dataverse_hub.model.Installation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RestHelperServiceTests {

    @Autowired
    RestUtilService restUtilService;

    @Test
    public void testRetrieveRestAPIObject() {
        
        String url = "https://raw.githubusercontent.com/IQSS/dataverse-installations/refs/heads/main/data/data.json";
        assertDoesNotThrow(() -> {
            InstallationGitImporter.InstallationWrapper installationList
                    = restUtilService.retrieveRestAPIObject(url, InstallationGitImporter.InstallationWrapper.class);
            assertNotNull(installationList);
        });

        assertThrows(JsonMappingException.class, () -> {

            Installation[] installationList
                    = restUtilService.retrieveRestAPIObject(url, Installation[].class);
                    assertNotEquals(installationList.length, 0);

                    
        });

    }
}
