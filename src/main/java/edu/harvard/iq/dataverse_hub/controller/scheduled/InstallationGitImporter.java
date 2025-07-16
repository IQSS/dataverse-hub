package edu.harvard.iq.dataverse_hub.controller.scheduled;


import edu.harvard.iq.dataverse_hub.service.RestUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.service.InstallationService;
import edu.harvard.iq.dataverse_hub.service.ScheduledJobService;
import java.util.List;
import java.util.ArrayList;

@Component
public class InstallationGitImporter {

    Logger logger = LoggerFactory.getLogger(InstallationGitImporter.class);

    public static final String INSTALLATIONS_URL = "https://raw.githubusercontent.com/IQSS/dataverse-installations/refs/heads/main/data/data.json";
    private final String JOB_NAME = this.getClass().getSimpleName();

    @Autowired
    private InstallationService installationService;
    
    @Autowired
    private ScheduledJobService scheduledJobService;

    @Autowired
    private RestUtilService restUtilService;

    @Scheduled(fixedRate = 21600000)
    public List<Installation> runTask() {
        logger.info("Starting {} job", JOB_NAME);
        try {
            List<Installation> installations = startTask(null);
            logger.info("Job {} successfully completed", JOB_NAME);
            return installations;
        } catch (Exception e) {
            logger.error("Problem running job {}", JOB_NAME, e);
            return null;
        }
    }

    /**
     * @param isDue will start the task depending on if is due or not.
     * @return the list of installations added.
     */
    public List<Installation> startTask(Boolean isDue) throws JsonProcessingException {

        if(isDue == null){
            isDue = scheduledJobService.isDue(JOB_NAME);
        }
        return isDue ? importInstallations(INSTALLATIONS_URL) : null;
    }


    /**
     * @param url the url of the json file to be used to import the installations.
     * @return the list of installations extracted from the JSON file and saved on the database.
     */
    public List<Installation> importInstallations(String url) throws JsonProcessingException {
        List<Installation> installationsDtos = null;
        try {
            GitHubInstallationWrapper installationsMapper = restUtilService.retrieveRestAPIObject(url, GitHubInstallationWrapper.class);

            installationsDtos = InstallationGitImporter.transform(installationsMapper);
            List<Installation> missingInstallations = installationService.getMissingInstallations(installationsDtos);
            for (Installation missingInstallation : missingInstallations) {
                missingInstallation.setActive(false);
            }
            
            missingInstallations = installationService.saveAllInstallations(missingInstallations);
            installationsDtos = installationService.saveAllInstallations(installationsDtos);            
            scheduledJobService.saveTransactionLog(JOB_NAME, 1);
        } catch (Exception e) {
            scheduledJobService.saveTransactionLog(JOB_NAME, -1);
            throw e;
        }
        return installationsDtos;
    }

    /**
     * Helper method to transform the installationWrapper from the source to the installation
     * @param installationWrapper the object that contain an installation.
     * @return the installation DAO with the properties of the wrapper.
     */
    public static Installation transform(InstallationWrapper installationWrapper) {
        if (installationWrapper == null) {
            throw new IllegalArgumentException("InstallationWrapper cannot be null");
        }
        Installation installation = new Installation();        
        installation.setHostname(installationWrapper.hostname); 
        installation.setName(installationWrapper.name);
        installation.setDescription(installationWrapper.description);
        installation.setLatitude(installationWrapper.latitude);
        installation.setLongitude(installationWrapper.longitude);
        installation.setActive(true);
        installation.setCountry(installationWrapper.country);
        installation.setContinent(installationWrapper.continent);
        installation.setLaunchYear(installationWrapper.launchYear);
        installation.setGdccMember(installationWrapper.gdccMember);
        installation.setDoiAuthority(installationWrapper.doiAuthority);
        installation.setContactEmail(installationWrapper.contactEmail);
        return installation;
    }

    /**
     * Return a list of installation DAOs extracted from the JSON file from GH
     * @param gitHubInstallationWrapper the wrapper that contains the installations on GH.
     * @return the list of equivalent DAOs for the installations.
     */
    private static List<Installation> transform(GitHubInstallationWrapper gitHubInstallationWrapper) {
        List<Installation> installations = new ArrayList<Installation>();
        for (InstallationWrapper installationWrapper : gitHubInstallationWrapper.installations) {
            installations.add(transform(installationWrapper));
        }

        return installations;
    }

    /**
     * Class that models the container of the installations.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GitHubInstallationWrapper {
        @JsonProperty("installations")
        List<InstallationWrapper> installations;
    
    }

    /**
     * A dataverse installation.
     */
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