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

        assertEquals(installation, installation);

        Installation installation2 = new Installation();
        installation2.setHostname("localhost");
        installation2.setName("installation test");
        installation2.setLatitude(1.0);
        installation2.setLongitude(1.0);
        installation2.setContactEmail("email");
        installation2.setContinent("continent");
        installation2.setCountry("country");
        installation2.setDescription("description");
        installation2.setLaunchYear(2024);
        installation2.setGdccMember(true);
        installation2.setDoiAuthority("doi");
        assertEquals(installation, installation2);

        installation2.updateWith(installation);

        assertDoesNotThrow(() -> {
            installation2.toString();
        });

        assertNull(installation2.getDvHubId());
        
    }

}
