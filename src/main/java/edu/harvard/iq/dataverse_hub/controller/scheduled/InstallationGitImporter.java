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
    public List<Installation> runTask() {

        System.out.println("Checking task status: " + jobName);
        if(scheduledJobService.isDue(jobName)){
            System.out.println("Running task: " + jobName);
            return importInstallations();
        }
        return null;
    }

    /**
     * This method is called by the scheduled task to import installations from the file located at the specified GitHub repository.
     */
    private List<Installation> importInstallations() {


        RestTemplate restTemplate = new RestTemplate();
        String jsonImport = restTemplate.getForObject(INSTALLATIONS_URL, String.class); 
        List<Installation> installationsDtos = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            GitHubInstallationWrapper installationsMapper = mapper.readValue(jsonImport, GitHubInstallationWrapper.class);

            installationsDtos = InstallationGitImporter.transform(installationsMapper);
            for (Installation installation : installationsDtos) {
                Installation existingInstallation = installationService.findByDVHubId(installation.getDvHubId());
                if (existingInstallation == null) {
                    installationService.save(installation);
                } else {
                    if(installation.equals(existingInstallation)){
                        existingInstallation.updateWith(installation);
                        installationService.save(existingInstallation);
                    }
                }
            }
            scheduledJobService.saveTransactionLog(jobName, 1);

        } catch (Exception e) {
            scheduledJobService.saveTransactionLog(jobName, -1);
        }

        return installationsDtos;
    }

    public static Installation transform(InstallationWrapper installationWrapper) {
        if (installationWrapper == null) {
            throw new IllegalArgumentException("InstallationWrapper cannot be null");
        }
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
    static class GitHubInstallationWrapper {
        @JsonProperty("installations")
        List<InstallationWrapper> installations;
    
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class InstallationWrapper {

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

        public void setName(String name) {
            this.name = name;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setDataverseVersion(String dataverseVersion) {
            this.dataverseVersion = dataverseVersion;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public void setClientInstitutionId(String clientInstitutionId) {
            this.clientInstitutionId = clientInstitutionId;
        }

        public void setContinent(String continent) {
            this.continent = continent;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setAdditionalContactInformation(String additionalContactInformation) {
            this.additionalContactInformation = additionalContactInformation;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public void setLaunchYear(Integer launchYear) {
            this.launchYear = launchYear;
        }

        public void setGdccMember(boolean gdccMember) {
            this.gdccMember = gdccMember;
        }

        public void setDoiAuthority(String doiAuthority) {
            this.doiAuthority = doiAuthority;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }
    }

}