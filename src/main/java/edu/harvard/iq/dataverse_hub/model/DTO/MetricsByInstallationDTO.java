package edu.harvard.iq.dataverse_hub.model.DTO;

import java.util.List;

import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationMetrics;
import io.swagger.v3.oas.annotations.media.Schema;

public class MetricsByInstallationDTO {

    private String dvHubId;

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

    private List<InstallationMetrics> metrics;

    public MetricsByInstallationDTO(Installation installation) {
        this.dvHubId = installation.getDvHubId();
        this.name = installation.getName();
        this.country = installation.getCountry();
        this.continent = installation.getContinent();
        this.launchYear = installation.getLaunchYear();
        this.metrics = installation.getMetrics();
    }



    public String getDvHubId() {
        return this.dvHubId;
    }

    public void setDvHubId(String dvHubId) {
        this.dvHubId = dvHubId;
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

   

    
}
