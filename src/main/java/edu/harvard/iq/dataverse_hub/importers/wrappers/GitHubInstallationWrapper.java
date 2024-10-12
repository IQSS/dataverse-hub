package edu.harvard.iq.dataverse_hub.importers.wrappers;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubInstallationWrapper {

    @JsonProperty("installations")
    List<InstallationWrapper> installations;


    public List<InstallationWrapper> getInstallations() {
        return this.installations;
    }

    public void setInstallations(List<InstallationWrapper> installations) {
        this.installations = installations;
    }


}
