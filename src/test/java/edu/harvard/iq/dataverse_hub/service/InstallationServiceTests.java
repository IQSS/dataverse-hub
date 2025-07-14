package edu.harvard.iq.dataverse_hub.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.harvard.iq.dataverse_hub.controller.scheduled.InstallationGitImporter;
import edu.harvard.iq.dataverse_hub.controller.scheduled.InstallationGitImporter.InstallationWrapper;
import edu.harvard.iq.dataverse_hub.model.Installation;

@SpringBootTest
public class InstallationServiceTests {

    @Autowired
    InstallationService installationService;

    @Test
    public void testInstallationService() {
        assertDoesNotThrow(() -> {
            installationService.findAll();
            installationService.getInstallationInfo();

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

            installationService.save(installationDTO);

            installationDTO.setHostname("hostname");
            
            installationService.save(installationDTO);
            assertNotNull(installationService.findById("hostname"));

        });
        
        

    }

}
