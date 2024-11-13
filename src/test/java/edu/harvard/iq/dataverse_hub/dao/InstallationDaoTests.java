package edu.harvard.iq.dataverse_hub.dao;

import edu.harvard.iq.dataverse_hub.model.Installation;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InstallationDaoTests {

    @Test
    public void testInstallationDao() {
        Installation installation = new Installation();
        installation.setHostname("localhost");
        installation.setName("installation test");
        installation.setLatitude(1.0);
        installation.setLongitude(1.0);
        installation.setContactEmail("email");
        installation.setContinent("continent");
        installation.setCountry("country");
        installation.setDescription("description");
        installation.setLaunchYear(2024);
        installation.setGdccMember(true);
        installation.setDoiAuthority("doi");

        
        assertNull(installation.getDvHubId());
        assertNotNull(installation.toString());
        
    }



}
