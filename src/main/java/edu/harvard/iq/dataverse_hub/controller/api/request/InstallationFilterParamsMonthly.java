package edu.harvard.iq.dataverse_hub.controller.api.request;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.Parameter;

public class InstallationFilterParamsMonthly {

    @Parameter(description = "Dataverse installation id for monthly metrics search", example = "dvh-0001-2018")
    private String dvHubId;

    @Parameter(description = "Name of the installation for monthly metrics search", example = "Harvard Dataverse")
    private String installationName;

    @Parameter(description = "Country of the installation for monthly metrics search", example = "USA")
    private String country;

    @Parameter(description = "Continent of the installation for monthly metrics search", example = "North America")
    private String continent;

    @Parameter(description = "Year of the installation launch for monthly metrics search", example = "2020")
    private Integer launchYear;

    @Parameter(description = "GDCC member status of the installation for monthly metrics search", example = "true")
    private Boolean gdccMember;

    @Parameter(description = "Maximum number of files in the installation for monthly metrics search", example = "100000")
    private Integer maxFiles;

    @Parameter(description = "Minimum number of files in the installation for monthly metrics search", example = "100")
    private Integer minFiles;

    @Parameter(description = "Maximum number of datasets in the installation for monthly metrics search", example = "10000")
    private Integer maxDatasets;

    @Parameter(description = "Minimum number of datasets in the installation for monthly metrics search", example = "100")
    private Integer minDatasets;

    @Parameter(description = "Maximum number of dataverses in the installation for monthly metrics search", example = "100")
    private Integer maxDataverses;

    @Parameter(description = "Minimum number of dataverses in the installation for monthly metrics search", example = "10")
    private Integer minDataverses;

    @Parameter(description = "Maximum number of harvested datasets in the installation for monthly metrics search", example = "1000")
    private Integer maxHarvested;

    @Parameter(description = "Minimum number of harvested datasets in the installation for monthly metrics search", example = "100")
    private Integer minHarvested;

    @Parameter(description = "Minimum number of local datasets in the installation for monthly metrics search", example = "100")
    private Integer minLocalDatasets;

    @Parameter(description = "Maximum number of local datasets in the installation for monthly metrics search", example = "1000")
    private Integer maxLocalDatasets;

    @Parameter(description = "Specified year and month to begin the search", example = "2020-12")
    private String fromDate;

    @Parameter(description = "Specified year and month to limit the search", example = "2020-12")
    private String toDate;

    // Getters and Setters
    public String getDvHubId() {
        return dvHubId;
    }

    public void setDvHubId(String dvHubId) {
        this.dvHubId = dvHubId;
    }

    public String getInstallationName() {
        return installationName;
    }

    public void setInstallationName(String installationName) {
        this.installationName = installationName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Integer getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(Integer launchYear) {
        this.launchYear = launchYear;
    }

    public Boolean getGdccMember() {
        return gdccMember;
    }

    public void setGdccMember(Boolean gdccMember) {
        this.gdccMember = gdccMember;
    }

    public Integer getMaxFiles() {
        return maxFiles;
    }

    public void setMaxFiles(Integer maxFiles) {
        this.maxFiles = maxFiles;
    }

    public Integer getMinFiles() {
        return minFiles;
    }

    public void setMinFiles(Integer minFiles) {
        this.minFiles = minFiles;
    }

    public Integer getMaxDatasets() {
        return maxDatasets;
    }

    public void setMaxDatasets(Integer maxDatasets) {
        this.maxDatasets = maxDatasets;
    }

    public Integer getMinDatasets() {
        return minDatasets;
    }

    public void setMinDatasets(Integer minDatasets) {
        this.minDatasets = minDatasets;
    }

    public Integer getMaxDataverses() {
        return maxDataverses;
    }

    public void setMaxDataverses(Integer maxDataverses) {
        this.maxDataverses = maxDataverses;
    }

    public Integer getMinDataverses() {
        return minDataverses;
    }

    public void setMinDataverses(Integer minDataverses) {
        this.minDataverses = minDataverses;
    }

    public Integer getMaxHarvested() {
        return maxHarvested;
    }

    public void setMaxHarvested(Integer maxHarvested) {
        this.maxHarvested = maxHarvested;
    }

    public Integer getMinHarvested() {
        return minHarvested;
    }

    public void setMinHarvested(Integer minHarvested) {
        this.minHarvested = minHarvested;
    }

    public Integer getMinLocalDatasets() {
        return minLocalDatasets;
    }

    public void setMinLocalDatasets(Integer minLocalDatasets) {
        this.minLocalDatasets = minLocalDatasets;
    }

    public Integer getMaxLocalDatasets() {
        return maxLocalDatasets;
    }

    public void setMaxLocalDatasets(Integer maxLocalDatasets) {
        this.maxLocalDatasets = maxLocalDatasets;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "{" +
            " dvHubId='" + getDvHubId() + "'" +
            ", installationName='" + getInstallationName() + "'" +
            ", country='" + getCountry() + "'" +
            ", continent='" + getContinent() + "'" +
            ", launchYear='" + getLaunchYear() + "'" +
            ", gdccMember='" + getGdccMember() + "'" +
            ", maxFiles='" + getMaxFiles() + "'" +
            ", minFiles='" + getMinFiles() + "'" +
            ", maxDatasets='" + getMaxDatasets() + "'" +
            ", minDatasets='" + getMinDatasets() + "'" +
            ", maxDataverses='" + getMaxDataverses() + "'" +
            ", minDataverses='" + getMinDataverses() + "'" +
            ", maxHarvested='" + getMaxHarvested() + "'" +
            ", minHarvested='" + getMinHarvested() + "'" +
            ", minLocalDatasets='" + getMinLocalDatasets() + "'" +
            ", maxLocalDatasets='" + getMaxLocalDatasets() + "'" +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            "}";
    }
}
