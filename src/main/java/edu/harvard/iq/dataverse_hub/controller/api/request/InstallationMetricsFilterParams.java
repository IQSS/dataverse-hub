package edu.harvard.iq.dataverse_hub.controller.api.request;

import io.swagger.v3.oas.annotations.Parameter;

public class InstallationMetricsFilterParams {

    @Parameter(description = "Name of the installation", example = "Harvard Dataverse")
    private String installationName;

    @Parameter(description = "Country of the installation", example = "USA")
    private String country;

    @Parameter(description = "Continent of the installation", example = "North America")
    private String continent;

    @Parameter(description = "Year of the installation launch", example = "2020")
    private Integer launchYear;

    @Parameter(description = "GDCC member status of the installation", example = "true")
    private Boolean gdccMember;

    @Parameter(description = "Maximum number of files in the installation", example = "100000")
    private Integer maxFiles;

    @Parameter(description = "Minimum number of files in the installation", example = "100")
    private Integer minFiles;

    @Parameter(description = "Maximum number of datasets in the installation", example = "10000")
    private Integer maxDatasets;

    @Parameter(description = "Minimum number of datasets in the installation", example = "100")
    private Integer minDatasets;

    @Parameter(description = "Maximum number of dataverses in the installation", example = "100")
    private Integer maxDataverses;

    @Parameter(description = "Minimum number of dataverses in the installation", example = "10")
    private Integer minDataverses;

    @Parameter(description = "Maximum number of harvested datasets in the installation", example = "1000")
    private Integer maxHarvested;

    @Parameter(description = "Minimum number of harvested datasets in the installation", example = "100")
    private Integer minHarvested;

    @Parameter(description = "Minimum number of local datasets in the installation", example = "100")
    private Integer minLocalDatasets;

    @Parameter(description = "Maximum number of local datasets in the installation", example = "1000")
    private Integer maxLocalDatasets;

    @Parameter(description = "Indicates if the installation is currently monitored", example = "true")
    private Boolean isActive;

    @Parameter(description = "Hostname of the installation", example = "dataverse.harvard.edu")
    private String hostname;

    // Getters and Setters
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String toString() {
        return "{" +
            " hostname='" + getHostname() + "'" +
            ", isActive='" + getIsActive() + "'" +
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
            "}";
    }
}
