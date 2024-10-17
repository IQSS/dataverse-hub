package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.harvard.iq.dataverse_hub.model.ScheduledJobTransactionLog;

public interface ScheduledJobTransactionLogRepo extends JpaRepository<ScheduledJobTransactionLog, Integer> {
    
    @Query("SELECT j FROM ScheduledJobTransactionLog j WHERE j.jobId = ?1")
    ScheduledJobTransactionLog findByJobId(Integer jobId);

    @Query("""
            SELECT j FROM ScheduledJobTransactionLog j WHERE j.jobId = ?1
            AND j.status = 1
            AND j.executionTime = (
                SELECT MAX(j.executionTime) FROM ScheduledJobTransactionLog j
                WHERE j.jobId = ?1
                AND j.status = 1
            )
            """)
    ScheduledJobTransactionLog findLatestTransactionByJobId(Integer jobId);

}
