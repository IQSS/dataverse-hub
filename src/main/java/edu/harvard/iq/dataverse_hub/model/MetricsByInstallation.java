package edu.harvard.iq.dataverse_hub.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "installation")
public class MetricsByInstallation {

    @Id
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

    @OneToMany(mappedBy = "installation", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<InstallationMetrics> metrics;


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
