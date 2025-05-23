package edu.harvard.iq.dataverse_hub.scheduled;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import edu.harvard.iq.dataverse_hub.controller.scheduled.VersionDVInstallationCheck;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.service.InstallationService;

@SpringBootTest
public class VersionDVInstallationCheckTests {

    @Autowired
    VersionDVInstallationCheck versionDVInstallationCheck;

    @Autowired
    InstallationService installationService;

    @Test
    public void testVersionCheck(){

        ArrayList<Installation> installationList = new ArrayList<Installation>();

        Installation installation = installationService.findAll().get(0); 
        installationList.add(installation);

        Installation invalidInstallation = installationService.findAll().get(1); 
        invalidInstallation.setHostname("invalid.url.ex");  
        installationList.add(invalidInstallation);

        assertDoesNotThrow(() -> {
            versionDVInstallationCheck.importInstallationsStatus(installationList);
        });

    }

    

}
