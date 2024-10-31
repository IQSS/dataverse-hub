package edu.harvard.iq.dataverse_hub.model;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class InstallationVersionInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "installation_version_info_seq")    
    @SequenceGenerator(
        name = "installation_version_info_seq", 
        sequenceName = "installation_version_info_seq", 
        allocationSize = 1)
    private Integer record_id;
    @ManyToOne
    @JoinColumn(name = "dv_hub_id")
    private Installation installation;
    private String status;
    private String version;
    private String build;
    private Date captureDate;

    public Integer getRecordId() {
        return this.record_id;
    }

    public void setRecordId(Integer record_id) {
        this.record_id = record_id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuild() {
        return this.build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public Date getCaptureDate() {
        return this.captureDate;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }


    public Integer getRecord_id() {
        return this.record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }

    public Installation getInstallation() {
        return this.installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }

    @Override
    public String toString() {
        return "{" +
            " record_id='" + getRecord_id() + "'" +
            ", installation='" + getInstallation() + "'" +
            ", status='" + getStatus() + "'" +
            ", version='" + getVersion() + "'" +
            ", build='" + getBuild() + "'" +
            ", captureDate='" + getCaptureDate() + "'" +
            "}";
    }



}
