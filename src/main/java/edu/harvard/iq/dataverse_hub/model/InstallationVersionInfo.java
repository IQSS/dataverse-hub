package edu.harvard.iq.dataverse_hub.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@Schema(description = "A representation of the version of a Dataverse installation")
public class InstallationVersionInfo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "installation_version_info_seq")    
    @SequenceGenerator(
        name = "installation_version_info_seq", 
        sequenceName = "installation_version_info_seq", 
        allocationSize = 1)
    @Schema(description = "Unique identifier for the version record", 
            example = "1")
    private Integer recordId;
    
    @ManyToOne
    @JoinColumn(name = "hostname")    
    private Installation installation;
    
    @Schema(description = "Status of the Dataverse installation",
            example = "ServiceUnavailable")
    private String status;
    
    @Schema(description = "Version of the Dataverse installation",
            example = "6.4.0")
    private String version;
    
    @Schema(description = "Build of the Dataverse installation",
            example = "1609-906f87")
    private String build;
    
    @Schema(description = "Date when the version information was captured",
            example = "2024-10-31T20:13:03.422+00:00")
    private Date recordDate;


    public Integer getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Installation getInstallation() {
        return this.installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
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

    public Date getRecordDate() {
        return this.recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public String toString() {
        return "{" +
            " recordId='" + getRecordId() + "'" +
            ", installation='" + getInstallation() + "'" +
            ", status='" + getStatus() + "'" +
            ", version='" + getVersion() + "'" +
            ", build='" + getBuild() + "'" +
            ", recordDate='" + getRecordDate() + "'" +
            "}";
    }
    

}
