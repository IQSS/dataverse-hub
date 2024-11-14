package edu.harvard.iq.dataverse_hub.repository;

import edu.harvard.iq.dataverse_hub.model.DevMetrics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DevMetricsRepo extends JpaRepository<DevMetrics, Integer> {

    @Query("""
            SELECT dm FROM DevMetrics dm
            WHERE dm.recordDate = (
                SELECT MAX(dm_sub.recordDate) 
                FROM DevMetrics dm_sub
            )
            """)
    public DevMetrics getLatestDevMetrics();

}
