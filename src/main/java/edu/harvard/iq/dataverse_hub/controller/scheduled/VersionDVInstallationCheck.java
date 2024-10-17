package edu.harvard.iq.dataverse_hub.controller.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.service.InstallationService;

@Component
public class VersionDVInstallationCheck {

    @Autowired
    private InstallationService installationService;

    @Scheduled(fixedRate = 3600000)
    public void runTask() {
        
        List<Installation> dvInstallationsList = installationService.findAll();
        RestTemplate restTemplate = new RestTemplate();

        for (Installation installation : dvInstallationsList) {
            System.out.println(installation.getHostname());

            try {
                String url = "https://" + installation.getHostname() + "/api/info/version";
                VersionInfo jsonImport = restTemplate.getForObject(url, VersionInfo.class); 
                System.out.println(jsonImport.data.version);
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Error: " + installation.getHostname() + " is not responding.");
            }
        }
        System.out.println("Checking for new Dataverse installations");
    
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class VersionInfo {
        @JsonProperty("status")
        private String status;
        
        @JsonProperty("data")
        private VersionData data;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class VersionData {

        @JsonProperty("version")
        private String version;

        @JsonProperty("build")
        private String build;
    }


}
