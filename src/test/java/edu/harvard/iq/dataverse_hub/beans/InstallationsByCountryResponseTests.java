package edu.harvard.iq.dataverse_hub.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.harvard.iq.dataverse_hub.controller.api.response.InstallationsByCountry;

public class InstallationsByCountryResponseTests {

    @Test
    public void testInstallationsByCountryResponse() {
        InstallationsByCountry installationsByCountryResponse = new InstallationsByCountry();
        installationsByCountryResponse.setCountry("country");
        installationsByCountryResponse.setCount(1l);
        assertEquals(installationsByCountryResponse.getCountry(), "country");
        assertEquals(installationsByCountryResponse.getCount(), 1);
        assertNotNull(installationsByCountryResponse.toString());
    }

}
