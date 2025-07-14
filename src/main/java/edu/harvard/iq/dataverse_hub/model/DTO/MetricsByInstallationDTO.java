package edu.harvard.iq.dataverse_hub.model.DTO;

import java.io.Serializable;
import java.util.List;

import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationMetrics;
import io.swagger.v3.oas.annotations.media.Schema;

public class MetricsByInstallationDTO implements Serializable {

    @Schema(description = "Unique identifier and address for the Dataverse installation",
            example = "dataverse.harvard.edu")
    private String hostname;

    @Schema(description = "Name of the Dataverse installation",
            example = "Harvard Dataverse")
    private String name;

    @Schema(description = "Country of the Dataverse installation",
            example = "United States")
    private String country;

    @Schema(description = "Continent of the Dataverse installation",
            example = "North America")
    private String continent;

    @Schema(description = "Year of launch of the Dataverse installation",
            example = "2008")
    private Integer launchYear;

    @Schema(description = "Indicates whether the Dataverse installation is active",
            example = "true")
    private Boolean active;

    private List<InstallationMetrics> metrics;

    public MetricsByInstallationDTO(Installation installation) {
        this.hostname = installation.getHostname();
        this.name = installation.getName();
        this.country = installation.getCountry();
        this.continent = installation.getContinent();
        this.launchYear = installation.getLaunchYear();
        this.metrics = installation.getMetrics();
        this.active = installation.getActive();
    }



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return this.continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Integer getLaunchYear() {
        return this.launchYear;
    }

    public void setLaunchYear(Integer launchYear) {
        this.launchYear = launchYear;
    }

    public List<InstallationMetrics> getMetrics() {
        return this.metrics;
    }

    public void setMetrics(List<InstallationMetrics> metrics) {
        this.metrics = metrics;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

   

    
}
