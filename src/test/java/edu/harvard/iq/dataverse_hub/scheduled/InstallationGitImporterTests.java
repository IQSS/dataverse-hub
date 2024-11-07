package edu.harvard.iq.dataverse_hub.scheduled;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonParseException;

import edu.harvard.iq.dataverse_hub.controller.scheduled.InstallationGitImporter;
import edu.harvard.iq.dataverse_hub.controller.scheduled.InstallationGitImporter.InstallationWrapper;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.service.ScheduledJobService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InstallationGitImporterTests {

    @Autowired
    InstallationGitImporter installationGitImporter;

    @Autowired 
    ScheduledJobService scheduledJobService;

    @Test
    public void testImportInstallation() {

        Boolean isDue = scheduledJobService.isDue(installationGitImporter.getClass().getSimpleName());
        assertNotNull(isDue);
        
        assertDoesNotThrow(() -> {
            assertNotNull(installationGitImporter.startTask(true));
        });

        assertDoesNotThrow(() -> {
            assertNull(installationGitImporter.startTask(false));
        });


        assertThrows(IllegalArgumentException.class, () -> {
            InstallationGitImporter.transform(null);
        });

        InstallationWrapper installationWrapper = new InstallationWrapper();
        installationWrapper.setName("Test Installation");
        installationWrapper.setUrl("https://example.com");
        installationWrapper.setDataverseVersion("6.0");
        installationWrapper.setLatitude(2.0);
        installationWrapper.setLongitude(2.0);
        installationWrapper.setClientInstitutionId("CI-123");
        installationWrapper.setContinent("NA");
        installationWrapper.setCountry("US");
        installationWrapper.setAdditionalContactInformation("646-123-4567");
        installationWrapper.setNotes("notes");
        installationWrapper.setDescription("description");
        installationWrapper.setHostname("hostname");
        installationWrapper.setLaunchYear(1985);
        installationWrapper.setDoiAuthority("DOIAUTH");
        installationWrapper.setGdccMember(false);
        installationWrapper.setContactEmail("email@email.com");
        Installation installationDTO = InstallationGitImporter.transform(installationWrapper);
        
        assertNotEquals(installationDTO, null);

        assertThrows(JsonParseException.class, () -> {
            installationGitImporter.importInstallations("https://example.com");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            installationGitImporter.importInstallations(null);
        });

    }

}
