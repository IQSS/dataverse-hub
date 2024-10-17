package edu.harvard.iq.dataverse_hub.controller.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.service.InstallationService;
import edu.harvard.iq.dataverse_hub.service.ScheduledJobService;

@Component
public class VersionDVInstallationCheck {

    @Autowired
    private InstallationService installationService;

    @Autowired
    private ScheduledJobService scheduledJobService;

    private String jobName = this.getClass().getSimpleName();

    @Scheduled(fixedRate = 60000)
    public void runTask(){
        if(scheduledJobService.isDue(jobName)){
            System.out.println("Running task: " + jobName);
            importInstallationsStatus();
        }
    }

    
    public void importInstallationsStatus() {
        try {
            List<Installation> dvInstallationsList = installationService.findAll();
            RestTemplate restTemplate = new RestTemplate();

            for (Installation installation : dvInstallationsList) {
                
     
                try {
                    String url = "https://" + installation.getHostname() + "/api/info/version";
                    VersionInfo jsonImport = restTemplate.getForObject(url, VersionInfo.class); 
                    installationService.logInstallationVersion(jsonImport, installation);                    
                } catch (UnknownContentTypeException ucte) {
                    installationService.logInstallationVersion(null, installation, "UNRECOGNIZABLE");                    
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                    installationService.logInstallationVersion(null, installation, "UNREACHABLE");                    
                }                
            }
            System.out.println("Checking for new Dataverse installations");
            scheduledJobService.saveTransactionLog(jobName, 1);
        } catch (Exception e) {
            scheduledJobService.saveTransactionLog(jobName, -1);
            e.printStackTrace();
        }            
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
