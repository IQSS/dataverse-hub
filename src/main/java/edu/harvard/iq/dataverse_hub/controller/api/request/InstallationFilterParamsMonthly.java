package edu.harvard.iq.dataverse_hub.controller.api.request;

import io.swagger.v3.oas.annotations.Parameter;

import java.io.Serializable;

public class InstallationFilterParamsMonthly implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstallationFilterParamsMonthly that = (InstallationFilterParamsMonthly) o;

        if (dvHubId != null ? !dvHubId.equals(that.dvHubId) : that.dvHubId != null) return false;
        if (installationName != null ? !installationName.equals(that.installationName) : that.installationName != null)
            return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (continent != null ? !continent.equals(that.continent) : that.continent != null) return false;
        if (launchYear != null ? !launchYear.equals(that.launchYear) : that.launchYear != null) return false;
        if (gdccMember != null ? !gdccMember.equals(that.gdccMember) : that.gdccMember != null) return false;
        if (maxFiles != null ? !maxFiles.equals(that.maxFiles) : that.maxFiles != null) return false;
        if (minFiles != null ? !minFiles.equals(that.minFiles) : that.minFiles != null) return false;
        if (maxDatasets != null ? !maxDatasets.equals(that.maxDatasets) : that.maxDatasets != null) return false;
        if (minDatasets != null ? !minDatasets.equals(that.minDatasets) : that.minDatasets != null) return false;
        if (maxDataverses != null ? !maxDataverses.equals(that.maxDataverses) : that.maxDataverses != null)
            return false;
        if (minDataverses != null ? !minDataverses.equals(that.minDataverses) : that.minDataverses != null)
            return false;
        if (maxHarvested != null ? !maxHarvested.equals(that.maxHarvested) : that.maxHarvested != null) return false;
        if (minHarvested != null ? !minHarvested.equals(that.minHarvested) : that.minHarvested != null) return false;
        if (minLocalDatasets != null ? !minLocalDatasets.equals(that.minLocalDatasets) : that.minLocalDatasets != null)
            return false;
        if (maxLocalDatasets != null ? !maxLocalDatasets.equals(that.maxLocalDatasets) : that.maxLocalDatasets != null)
            return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        return toDate != null ? toDate.equals(that.toDate) : that.toDate == null;
    }

    @Override
    public int hashCode() {
        int result = dvHubId != null ? dvHubId.hashCode() : 0;
        result = 31 * result + (installationName != null ? installationName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        result = 31 * result + (launchYear != null ? launchYear.hashCode() : 0);
        result = 31 * result + (gdccMember != null ? gdccMember.hashCode() : 0);
        result = 31 * result + (maxFiles != null ? maxFiles.hashCode() : 0);
        result = 31 * result + (minFiles != null ? minFiles.hashCode() : 0);
        result = 31 * result + (maxDatasets != null ? maxDatasets.hashCode() : 0);
        result = 31 * result + (minDatasets != null ? minDatasets.hashCode() : 0);
        result = 31 * result + (maxDataverses != null ? maxDataverses.hashCode() : 0);
        result = 31 * result + (minDataverses != null ? minDataverses.hashCode() : 0);
        result = 31 * result + (maxHarvested != null ? maxHarvested.hashCode() : 0);
        result = 31 * result + (minHarvested != null ? minHarvested.hashCode() : 0);
        result = 31 * result + (minLocalDatasets != null ? minLocalDatasets.hashCode() : 0);
        result = 31 * result + (maxLocalDatasets != null ? maxLocalDatasets.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        return result;
    }

    

    
}
