package edu.harvard.iq.dataverse_hub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ScheduledJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;
    private String description;
    private String jobName;
    private Integer frequency;

    public Integer getJobId() {
        return this.jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getFrequency() {
        return this.frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "{" +
            " jobId='" + getJobId() + "'" +
            ", description='" + getDescription() + "'" +
            ", jobName='" + getJobName() + "'" +
            ", frequency='" + getFrequency() + "'" +
            "}";
    }

}
