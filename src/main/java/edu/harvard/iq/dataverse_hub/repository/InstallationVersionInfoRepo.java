package edu.harvard.iq.dataverse_hub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;

public interface InstallationVersionInfoRepo extends JpaRepository<InstallationVersionInfo, Integer> {

    @Query("""
            SELECT ivi FROM InstallationVersionInfo ivi
            RIGHT JOIN FETCH InstallationVersionInfo ivi_r
            ON ivi.installation.dvHubId = ivi_r.installation.dvHubId 
            AND ivi.recordDate = (
                SELECT MAX(ivi_sub.recordDate) 
                FROM InstallationVersionInfo ivi_sub 
                WHERE ivi_sub.installation.dvHubId = ivi.installation.dvHubId
            )        
            """)    
    public List<InstallationVersionInfo> getLatestStatusAll();
}
