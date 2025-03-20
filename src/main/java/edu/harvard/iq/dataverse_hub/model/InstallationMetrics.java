package edu.harvard.iq.dataverse_hub.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Schema(description = "Dataverse installation metrics")
public class InstallationMetrics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "installation_metrics_seq")    
    @SequenceGenerator(
        name = "installation_metrics_seq", 
        sequenceName = "installation_metrics_seq", 
        allocationSize = 1)
    @JsonIgnore
    private Integer recordId;

    @ManyToOne
    @JoinColumn(name = "dv_hub_id")    
    @JsonBackReference
    private Installation installation;

    @Schema(description = "Date when the metrics were captured",
            example = "2024-10-31T20:13:03.422+00:00")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime recordDate;

    @Schema(description = "Number of files in the Dataverse installation",
            example = "100000")
    private Long files;

    @Schema(description = "Number of downloads from the Dataverse installation",
            example = "1000000")
    private Long downloads;

    @Schema(description = "Number of datasets in the Dataverse installation",
            example = "10000")
    private Integer datasets;

    @Schema(description = "Number of harvested datasets in the Dataverse installation",
            example = "1000")
    private Integer harvestedDatasets;

    @Schema(description = "Number of local datasets in the Dataverse installation",
            example = "9001")
    private Integer localDatasets;

    @Schema(description = "Number of dataverses in the Dataverse installation",
            example = "100")
    private Integer dataverses;


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

    public LocalDateTime getRecordDate() {
        return this.recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    public Long getFiles() {
        return this.files;
    }

    public void setFiles(Long files) {
        this.files = files;
    }

    public Long getDownloads() {
        return this.downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }

    public Integer getDatasets() {
        return this.datasets;
    }

    public void setDatasets(Integer datasets) {
        this.datasets = datasets;
    }

    public Integer getHarvestedDatasets() {
        return this.harvestedDatasets;
    }

    public void setHarvestedDatasets(Integer harvestedDatasets) {
        this.harvestedDatasets = harvestedDatasets;
    }

    public Integer getLocalDatasets() {
        return this.localDatasets;
    }

    public void setLocalDatasets(Integer localDatasets) {
        this.localDatasets = localDatasets;
    }

    public Integer getDataverses() {
        return this.dataverses;
    }

    public void setDataverses(Integer dataverses) {
        this.dataverses = dataverses;
    }

    @Override
    public String toString() {
        return "{" +
            " recordId='" + getRecordId() + "'" +
            ", installation='" + getInstallation() + "'" +
            ", recordDate='" + getRecordDate() + "'" +
            ", files='" + getFiles() + "'" +
            ", downloads='" + getDownloads() + "'" +
            ", datasets='" + getDatasets() + "'" +
            ", harvestedDatasets='" + getHarvestedDatasets() + "'" +
            ", localDatasets='" + getLocalDatasets() + "'" +
            ", dataverses='" + getDataverses() + "'" +
            "}";
    }




}
