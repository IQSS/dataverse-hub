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

    private final static int DEFAULT_FREQUENCY = 3600000;

    public ScheduledJob save(ScheduledJob scheduledJob) {
        return scheduledJobRepo.save(scheduledJob);
    }

    public List<ScheduledJob> findAll() {
        return scheduledJobRepo.findAll();
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
            scheduledJobRepo.save(jobConfig);
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

    public ScheduledJobTransactionLog saveTransactionLog(Integer jobId, Integer status) {
        ScheduledJobTransactionLog transactionLog = new ScheduledJobTransactionLog();
        transactionLog.setExecutionTime(new Date());
        transactionLog.setStatus(status);
        transactionLog.setJobId(jobId);
        return scheduledJobTransactionLogRepo.save(transactionLog);
    }

    public ScheduledJobTransactionLog saveTransactionLog(String jobName, Integer status) {
        ScheduledJob jobConfig = scheduledJobRepo.findByName(jobName);
        return saveTransactionLog(jobConfig.getJobId(),  status);
    }

}
