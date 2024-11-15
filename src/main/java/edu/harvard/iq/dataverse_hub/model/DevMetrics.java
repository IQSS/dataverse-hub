package edu.harvard.iq.dataverse_hub.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@Schema(description = "Development metrics of the Dataverse repository")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DevMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "development_metrics_seq")    
    @SequenceGenerator(
        name = "development_metrics_seq", 
        sequenceName = "development_metrics_seq", 
        allocationSize = 1)
    @JsonIgnore
    Integer recordId;

    @Schema(description = "Date when the metrics were captured",
            example = "2024-10-31T20:13:03.422+00:00")
    @JsonIgnore
    Date recordDate;

    @Schema(description = "Number of open issues on this repository", 
            example = "100")
    @JsonProperty("open_issues")
    Integer openIssues;

    @Schema(description = "Number of watchers on this repository", 
            example = "100")
    Integer watchers;

    @Schema(description = "Number of forks of this repository", 
            example = "100")
    Integer forks;
    
    @Schema(description = "Number of stars on this repository", 
            example = "100")
    @JsonProperty("subscribers_count")
    Integer subscribersCount;

    @Schema(description = "Name of the repository",
            example = "Dataverse")
    @JsonProperty("name")
    String repoName;


    public Integer getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getOpenIssues() {
        return this.openIssues;
    }

    public void setOpenIssues(Integer openIssues) {
        this.openIssues = openIssues;
    }

    public Integer getWatchers() {
        return this.watchers;
    }

    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    public Integer getForks() {
        return this.forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getSubscribersCount() {
        return this.subscribersCount;
    }

    public void setSubscribersCount(Integer subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    public String getRepoName() {
        return this.repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
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
            ", openIssues='" + getOpenIssues() + "'" +
            ", watchers='" + getWatchers() + "'" +
            ", forks='" + getForks() + "'" +
            ", subscribersCount='" + getSubscribersCount() + "'" +
            ", repoName='" + getRepoName() + "'" +
            ", record_date='" + getRecordDate() + "'" +
            "}";
    }

}
