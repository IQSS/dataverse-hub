package edu.harvard.iq.dataverse_hub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.harvard.iq.dataverse_hub.controller.api.payloadBeans.InstallationsByCountry;
import edu.harvard.iq.dataverse_hub.model.Installation;


public interface InstallationRepo extends JpaRepository<Installation, String> {
    
    @Query("SELECT i FROM Installation i WHERE i.dvHubId = ?1")
    Installation findByDVHubId(String dvHubId);

    @Query("""
            SELECT NEW edu.harvard.iq.dataverse_hub.controller.api.payloadBeans.InstallationsByCountry(
                i.country, 
                COUNT(i)) 
            FROM Installation i 
            GROUP BY i.country
            ORDER BY COUNT(i) DESC
            """)
    List<InstallationsByCountry> getInstallationsByCountry();

}
