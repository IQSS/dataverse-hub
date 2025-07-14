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
                SELECT installation.hostname AS hostname, MAX(recordDate) AS maxRecordDate
                FROM InstallationVersionInfo
                GROUP BY installation.hostname
            ) AS maxDates
            ON ivi.installation.hostname = maxDates.hostname
            AND ivi.recordDate = maxDates.maxRecordDate
            """) 
    public List<InstallationVersionInfo> getLatestStatusAll();
}
