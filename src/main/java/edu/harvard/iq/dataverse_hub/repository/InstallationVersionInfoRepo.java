package edu.harvard.iq.dataverse_hub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;

public interface InstallationVersionInfoRepo extends JpaRepository<InstallationVersionInfo, Integer> {

    @Query("""
            SELECT ivi 
            FROM InstallationVersionInfo ivi
            JOIN (
                SELECT installation.dvHubId AS dvHubId, MAX(recordDate) AS maxRecordDate
                FROM InstallationVersionInfo
                GROUP BY installation.dvHubId
            ) AS maxDates
            ON ivi.installation.dvHubId = maxDates.dvHubId 
            AND ivi.recordDate = maxDates.maxRecordDate
            """) 
    public List<InstallationVersionInfo> getLatestStatusAll();
}
