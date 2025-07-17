package edu.harvard.iq.dataverse_hub.controller.api.request;

import io.swagger.v3.oas.annotations.Parameter;

import java.io.Serializable;

public class InstallationFilterParams implements Serializable {


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
            "hostname='" + getHostname() + "'" +
            ", installationName='" + getInstallationName() + "'" +
            ", country='" + getCountry() + "'" +
            ", continent='" + getContinent() + "'" +
            ", launchYear='" + getLaunchYear() + "'" +
            ", gdccMember='" + getGdccMember() + "'" +
            ", isActive='" + getIsActive() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstallationFilterParams that = (InstallationFilterParams) o;
        if (hostname != null ? !hostname.equals(that.hostname) : that.hostname != null) return false;
        if( isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;
        if (installationName != null ? !installationName.equals(that.installationName) : that.installationName != null)
            return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (continent != null ? !continent.equals(that.continent) : that.continent != null) return false;
        if (launchYear != null ? !launchYear.equals(that.launchYear) : that.launchYear != null) return false;
        if (gdccMember != null ? !gdccMember.equals(that.gdccMember) : that.gdccMember != null) return false;

        return true;
    }

    /**
     * Returns a hash code value for the object, this is used for caching.
     */
    @Override
    public int hashCode() {
        int result = hostname != null ? hostname.hashCode() : 0;
        result = 31 * result + (installationName != null ? installationName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        result = 31 * result + (launchYear != null ? launchYear.hashCode() : 0);
        result = 31 * result + (gdccMember != null ? gdccMember.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
    
}
