package edu.harvard.iq.dataverse_hub.controller.scheduled;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.service.InstallationService;
import edu.harvard.iq.dataverse_hub.service.ScheduledJobService;

import java.util.List;
import java.util.ArrayList;

@Component
public class InstallationGitImporter {

    private static final String INSTALLATIONS_URL = "https://raw.githubusercontent.com/IQSS/dataverse-installations/refs/heads/main/data/data.json";

    @Autowired
    private InstallationService installationService;
    
    @Autowired
    private ScheduledJobService scheduledJobService;

    private String jobName = this.getClass().getSimpleName();

    @Scheduled(fixedRate = 60000)
    public void runTask() {
        System.out.println("Checking task status: " + jobName);
        if(scheduledJobService.isDue(jobName)){
            System.out.println("Running task: " + jobName);
            importInstallations();
        }
    }

    /**
     * This method is called by the scheduled task to import installations from the file located at the specified GitHub repository.
     */
    private void importInstallations() {


        RestTemplate restTemplate = new RestTemplate();
        String jsonImport = restTemplate.getForObject(INSTALLATIONS_URL, String.class); 

        try {
            ObjectMapper mapper = new ObjectMapper();
            GitHubInstallationWrapper installations = mapper.readValue(jsonImport, GitHubInstallationWrapper.class);

            List<Installation> dtos = InstallationGitImporter.transform(installations);
            for (Installation installation : dtos) {
                Installation existingInstallation = installationService.findByDVHubId(installation.getDvHubId());
                if (existingInstallation == null) {
                    installationService.save(installation);
                } else {
                    //TODO UPDATE INSTALLATION
                }

            }

            scheduledJobService.saveTransactionLog(jobName, 1);

        } catch (Exception e) {
            scheduledJobService.saveTransactionLog(jobName, -1);
            e.printStackTrace();
        }

    }

    private static Installation transform(InstallationWrapper installationWrapper) {
        Installation installation = new Installation();
        installation.setDvHubId("DVN_" + installationWrapper.name.toUpperCase().replace(" ", "_") + "_" + installationWrapper.launchYear);
        installation.setName(installationWrapper.name);
        installation.setDescription(installationWrapper.description);
        installation.setLatitude(installationWrapper.latitude);
        installation.setLongitude(installationWrapper.longitude);
        installation.setHostname(installationWrapper.hostname); 
        installation.setCountry(installationWrapper.country);
        installation.setContinent(installationWrapper.continent);
        installation.setLaunchYear(installationWrapper.launchYear);
        installation.setGdccMember(installationWrapper.gdccMember);
        installation.setDoiAuthority(installationWrapper.doiAuthority);
        installation.setContactEmail(installationWrapper.contactEmail);
        return installation;
    }

    private static List<Installation> transform(GitHubInstallationWrapper gitHubInstallationWrapper) {
        List<Installation> installations = new ArrayList<Installation>();
        for (InstallationWrapper installationWrapper : gitHubInstallationWrapper.installations) {
            installations.add(transform(installationWrapper));
        }

        return installations;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class GitHubInstallationWrapper {
        @JsonProperty("installations")
        List<InstallationWrapper> installations;
    
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class InstallationWrapper {

        @JsonProperty("name")
        private String name;

        @JsonProperty("url")
        private String url;

        @JsonProperty("dataverseVersion")
        private String dataverseVersion;

        @JsonProperty("lat")
        private Double latitude;

        @JsonProperty("lng")
        private Double longitude;

        @JsonProperty("clientInstitutionId")
        private String clientInstitutionId;

        @JsonProperty("continent")
        private String continent;

        @JsonProperty("country")
        private String country;

        @JsonProperty("additionalContactInformation")
        private String additionalContactInformation;

        @JsonProperty("notes")
        private String notes;

        @JsonProperty("description")
        private String description;

        @JsonProperty("hostname")
        private String hostname;

        @JsonProperty("launch_year")
        private Integer launchYear;

        @JsonProperty("gdcc_member")
        private boolean gdccMember;

        @JsonProperty("doi_authority")
        private String doiAuthority;

        @JsonProperty("contact_email")
        private String contactEmail;
    
    }

}