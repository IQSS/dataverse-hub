package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.harvard.iq.dataverse_hub.model.ScheduledJob;

public interface ScheduledJobRepo extends JpaRepository<ScheduledJob, Integer> {
    
    @Query("SELECT j FROM ScheduledJob j WHERE j.jobId = ?1")
    ScheduledJob findByJobId(Integer jobId);

    @Query("SELECT j FROM ScheduledJob j WHERE j.jobName = ?1")
    ScheduledJob findByName(String jobName);
    
    //Boolean isDue();

}
