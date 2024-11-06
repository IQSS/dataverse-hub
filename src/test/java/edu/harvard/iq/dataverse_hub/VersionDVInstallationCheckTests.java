package edu.harvard.iq.dataverse_hub;

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

        Installation installationEr = installationService.findAll().get(1); 
        installationEr.setHostname("invalid.url.ex");  
        installationList.add(installationEr);

        assertDoesNotThrow(() -> {
            versionDVInstallationCheck.importInstallationsStatus(installationList);
        });

    }

    

}
