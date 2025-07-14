package edu.harvard.iq.dataverse_hub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
@Entity
@Schema(description = "Dataverse installation")
public class Installation implements java.io.Serializable {

    @Id
    private String hostname;
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    private String country;
    private String continent;
    private Integer launchYear;
    private Boolean gdccMember;
    private String doiAuthority;
    private String contactEmail;
    private boolean active;
    
    @OneToMany(mappedBy = "installation", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<InstallationMetrics> metrics;

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

    public List<InstallationMetrics> getMetrics() {
        return this.metrics;
    }

    public void setMetrics(List<InstallationMetrics> metrics) {
        this.metrics = metrics;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
