package edu.harvard.iq.dataverse_hub.importers.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstallationWrapper {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("dataverseVersion")
    private String dataverseVersion;

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lng")
    private double longitude;

    @JsonProperty("contactEmail")
    private String contactEmail;

    @JsonProperty("clientInstitutionId")
    private String clientInstitutionId;

    @JsonProperty("continent")
    private String continent;

    @JsonProperty("country")
    private String country;

    @JsonProperty("additionalContactInformation")
    private String additionalContactInformation;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("description")
    private String description;

    @JsonProperty("hostname")
    private String hostname;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDataverseVersion() {
        return dataverseVersion;
    }

    public void setDataverseVersion(String dataverseVersion) {
        this.dataverseVersion = dataverseVersion;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getClientInstitutionId() {
        return clientInstitutionId;
    }

    public void setClientInstitutionId(String clientInstitutionId) {
        this.clientInstitutionId = clientInstitutionId;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdditionalContactInformation() {
        return additionalContactInformation;
    }

    public void setAdditionalContactInformation(String additionalContactInformation) {
        this.additionalContactInformation = additionalContactInformation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

