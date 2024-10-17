package edu.harvard.iq.dataverse_hub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;

public interface InstallationVersionInfoRepo extends JpaRepository<InstallationVersionInfo, Integer> {

    @Query("""
            SELECT ivi FROM InstallationVersionInfo ivi
            RIGHT JOIN FETCH InstallationVersionInfo ivi_r
            ON ivi.dvHubId = ivi_r.dvHubId 
            AND ivi.captureDate = (
                SELECT MAX(ivi_sub.captureDate) 
                FROM InstallationVersionInfo ivi_sub 
                WHERE ivi_sub.dvHubId = ivi.dvHubId
            )        
            """)
    public List<InstallationVersionInfo> getLatestStatusAll();
}
