package edu.harvard.iq.dataverse_hub.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.harvard.iq.dataverse_hub.model.ScheduledJob;
import edu.harvard.iq.dataverse_hub.model.ScheduledJobTransactionLog;
import edu.harvard.iq.dataverse_hub.repository.ScheduledJobRepo;
import edu.harvard.iq.dataverse_hub.repository.ScheduledJobTransactionLogRepo;

@Service
public class ScheduledJobService {

    @Autowired
    private ScheduledJobRepo scheduledJobRepo;

    @Autowired
    private ScheduledJobTransactionLogRepo scheduledJobTransactionLogRepo;

    private final static int DEFAULT_FREQUENCY = 86400000;

    public ScheduledJob save(ScheduledJob scheduledJob) {
        return scheduledJobRepo.save(scheduledJob);
    }

    public List<ScheduledJob> findAll() {
        return scheduledJobRepo.findAll();
    }

    public ScheduledJob deletScheduledJob(String jobName) {
        ScheduledJob jobConfig = scheduledJobRepo.findByName(jobName);
        if(jobConfig != null){
            scheduledJobRepo.delete(jobConfig);
        }
        return jobConfig;
    }

    /**
     * This method checks if a job is due to run based on the last time it was executed 
     * and the frequency of the job.
     * @param jobName
     * @return
     */
    public Boolean isDue(String jobName) {

        ScheduledJob jobConfig = scheduledJobRepo.findByName(jobName);
        if(jobConfig == null){
            jobConfig = new ScheduledJob();
            jobConfig.setJobName(jobName);
            jobConfig.setDescription(jobName);
            jobConfig.setFrequency(DEFAULT_FREQUENCY);
            jobConfig.setRecurring(true);
            scheduledJobRepo.save(jobConfig);
        } else if (!jobConfig.getRecurring()) {
            return false;
        }
        ScheduledJobTransactionLog lastTransaction =
            scheduledJobTransactionLogRepo.findLatestTransactionByJobId(
                jobConfig.getJobId());

        if (lastTransaction == null) {
            return true;
        } else {
            long currentTime = System.currentTimeMillis();
            long lastTransactionTime = lastTransaction.getExecutionTime().getTime();
            long frequency = jobConfig.getFrequency();
            if (currentTime - lastTransactionTime > frequency) {
                return true;
            }
        }

        return false;
    }

    public ScheduledJob disableRecurrence(String jobName) {
        ScheduledJob jobConfig = scheduledJobRepo.findByName(jobName);
        if(jobConfig != null){
            jobConfig.setRecurring(false);
            scheduledJobRepo.save(jobConfig);
        }
        return jobConfig;
    }

    public ScheduledJobTransactionLog saveTransactionLog(ScheduledJob job, Integer status) {
        ScheduledJobTransactionLog transactionLog = new ScheduledJobTransactionLog();
        transactionLog.setExecutionTime(new Date());
        transactionLog.setStatus(status);
        transactionLog.setJob(job);
        return scheduledJobTransactionLogRepo.save(transactionLog);
    }

    public ScheduledJobTransactionLog saveTransactionLog(String jobName, Integer status) {
        ScheduledJob jobConfig = scheduledJobRepo.findByName(jobName);
        return saveTransactionLog(jobConfig,  status);
    }

}
