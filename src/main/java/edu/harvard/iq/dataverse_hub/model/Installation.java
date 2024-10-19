package edu.harvard.iq.dataverse_hub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Installation {

    @Id
    @Schema(description = "Unique identifier for the Dataverse installation")
    private String dvHubId;
    @Schema(description = "Name of the Dataverse installation")
    private String name;
    @Schema(description = "Description of the Dataverse installation")
    private String description;
    @Schema(description = "Latitude of the Dataverse installation")
    private String latitude;
    @Schema(description = "Longitude of the Dataverse installation")
    private String longitude;
    @Schema(description = "Host address of the Dataverse installation")
    private String hostname;
    @Schema(description = "Country of the Dataverse installation")
    private String country;
    @Schema(description = "Continent of the Dataverse installation")
    private String continent;
    @Schema(description = "Year of launch of the Dataverse installation")
    private Integer launchYear;
    @Schema(description = "Whether the Dataverse installation is a member of the Global Dataverse Community Consortium")
    private Boolean gdccMember;
    @Schema(description = "DOI authority of the Dataverse installation")
    private String doiAuthority;
    @Schema(description = "Contact email of the admin of the Dataverse installation")
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

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
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

    public String getContact_email() {
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
                ", contact_email='" + contactEmail + '\'' +
                '}';
    }
}
