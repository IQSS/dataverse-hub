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
public class ScheduledJobTransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
        generator = "scheduled_job_transaction_log_seq")    
    @SequenceGenerator(name = "scheduled_job_transaction_log_seq", 
        sequenceName = "scheduled_job_transaction_log_seq", 
        allocationSize = 1)
    private Long transactionId;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private ScheduledJob job;
    private Date executionTime;
    private Integer status;

    public Long getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getExecutionTime() {
        return this.executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public ScheduledJob getJob() {
        return this.job;
    }

    public void setJob(ScheduledJob job) {
        this.job = job;
    }


    @Override
    public String toString() {
        return "{" +
            " transactionId='" + getTransactionId() + "'" +
            ", job='" + getJob() + "'" +
            ", executionTime='" + getExecutionTime() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
   



}
