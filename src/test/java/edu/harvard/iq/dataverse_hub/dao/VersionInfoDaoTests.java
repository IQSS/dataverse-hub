package edu.harvard.iq.dataverse_hub.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;

public class VersionInfoDaoTests {

    @Test
    public void testInfoDaoTest() {

        InstallationVersionInfo versionInfo = new InstallationVersionInfo();

        assertDoesNotThrow(()->{
            versionInfo.toString();
        });
    }

}
