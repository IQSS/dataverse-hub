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
            
            VersionInfo jsonImport = null;
            try {
                String url = "https://" + installation.getHostname() + "/api/info/version";
                jsonImport = restTemplate.getForObject(url, VersionInfo.class); 
                
            } catch (Exception e) {
                System.out.println("Error: " + installation.getHostname() + " is not responding.");
            }
            installationService.logInstallationVersion(jsonImport, installation);
        }
        System.out.println("Checking for new Dataverse installations");
    
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VersionInfo {
        @JsonProperty("status")
        public String status;
        
        @JsonProperty("data")
        public VersionData data;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VersionData {

        @JsonProperty("version")
        public String version;

        @JsonProperty("build")
        public String build;
    }


}
