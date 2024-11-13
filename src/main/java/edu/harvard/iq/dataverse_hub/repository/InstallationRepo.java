package edu.harvard.iq.dataverse_hub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.harvard.iq.dataverse_hub.controller.api.response.InstallationsByCountry;
import edu.harvard.iq.dataverse_hub.model.Installation;


public interface InstallationRepo extends JpaRepository<Installation, String> {
    
    @Query("SELECT i FROM Installation i WHERE i.dvHubId = ?1")
    Installation findByDVHubId(String dvHubId);

    @Query("""
            SELECT NEW edu.harvard.iq.dataverse_hub.controller.api.response.InstallationsByCountry(
                i.country, 
                COUNT(i)) 
            FROM Installation i 
            GROUP BY i.country
            ORDER BY COUNT(i) DESC
            """)
    List<InstallationsByCountry> getInstallationsByCountry();

    @Query("""
            SELECT i FROM Installation i 
            RIGHT JOIN FETCH InstallationVersionInfo ivi
            ON i.dvHubId = ivi.installation.dvHubId
            WHERE ivi.status = 'OK'
            AND ivi.captureDate = (
                SELECT MAX(ivi_sub.captureDate) 
                FROM InstallationVersionInfo ivi_sub 
                WHERE ivi_sub.installation.dvHubId = ivi.installation.dvHubId
            )   
            """)
    List<Installation> getHealthyInstallations();

}
