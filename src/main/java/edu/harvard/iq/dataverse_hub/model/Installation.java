package edu.harvard.iq.dataverse_hub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Entity
@Schema(description = "Dataverse installation")
public class Installation {

    @Id
    @Schema(description = "Unique identifier for the Dataverse installation", 
            example = "DVH_HARVARD_DATAVERSE_2008")
    private String dvHubId;

    @Schema(description = "Name of the Dataverse installation",
            example = "Harvard Dataverse")
    private String name;
    
    @Schema(description = "Description of the Dataverse installation",
            example = "Share, archive, and get credit for your data. Find and cite data across all research fields")
    private String description;

    @Schema(description = "Latitude of the Dataverse installation",
            example = "42.375646")
    private Double latitude;

    @Schema(description = "Longitude of the Dataverse installation",
            example = "-71.113212")
    private Double longitude;

    @Schema(description = "Host address of the Dataverse installation",
            example = "dataverse.harvard.edu")
    private String hostname;

    @Schema(description = "Country of the Dataverse installation",
            example = "United States")
    private String country;

    @Schema(description = "Continent of the Dataverse installation",
            example = "North America")
    private String continent;

    @Schema(description = "Year of launch of the Dataverse installation",
            example = "2008")
    private Integer launchYear;

    @Schema(description = "Whether the Dataverse installation is a member of the Global Dataverse Community Consortium",
            example = "true")
    private Boolean gdccMember;

    @Schema(description = "DOI authority of the Dataverse installation",
            example = "\\u2415")
    private String doiAuthority;

    @Schema(description = "Contact email of the admin of the Dataverse installation",
            example = "support@dataverse.harvard.edu")
    private String contactEmail;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
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

    public Boolean isGdccMember() {
        return this.gdccMember;
    }

    public Boolean getGdccMember() {
        return this.gdccMember;
    }

    public void setGdccMember(Boolean gdccMember) {
        this.gdccMember = gdccMember;
    }

    public String getDoiAuthority() {
        return this.doiAuthority;
    }

    public void setDoiAuthority(String doiAuthority) {
        this.doiAuthority = doiAuthority;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public void setContactEmail(String contact_email) {
        this.contactEmail = contact_email;
    }

    @Override
    public String toString() {
        return "Installation{" +
                "dvHubId='" + dvHubId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", hostname='" + hostname + '\'' +
                ", country='" + country + '\'' +
                ", continent='" + continent + '\'' +
                ", launchYear=" + launchYear +
                ", gdccMember=" + gdccMember +
                ", doiAuthority='" + doiAuthority + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                '}';
    }

    public Installation updateWith(Installation updatedInstallation) {
        this.name = updatedInstallation.getName();
        this.description = updatedInstallation.getDescription();
        this.latitude = updatedInstallation.getLatitude();
        this.longitude = updatedInstallation.getLongitude();
        this.hostname = updatedInstallation.getHostname();
        this.country = updatedInstallation.getCountry();
        this.continent = updatedInstallation.getContinent();
        this.launchYear = updatedInstallation.getLaunchYear();
        this.gdccMember = updatedInstallation.getGdccMember();
        this.doiAuthority = updatedInstallation.getDoiAuthority();
        this.contactEmail = updatedInstallation.getContactEmail();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Installation that = (Installation) o;
        return Objects.equals(getName(), that.getName()) && 
                Objects.equals(getDescription(), that.getDescription()) && 
                Objects.equals(getLatitude(), that.getLatitude()) && 
                Objects.equals(getLongitude(), that.getLongitude()) && 
                Objects.equals(getHostname(), that.getHostname()) && 
                Objects.equals(getCountry(), that.getCountry()) && 
                Objects.equals(getContinent(), that.getContinent()) && 
                Objects.equals(getLaunchYear(), that.getLaunchYear()) && 
                Objects.equals(getGdccMember(), that.getGdccMember()) && 
                Objects.equals(getDoiAuthority(), that.getDoiAuthority()) && 
                Objects.equals(getContactEmail(), that.getContactEmail());
    }

}
