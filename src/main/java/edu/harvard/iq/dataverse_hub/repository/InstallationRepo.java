package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.harvard.iq.dataverse_hub.model.Installation;


public interface InstallationRepo extends JpaRepository<Installation, String> {
    
    @Query("SELECT i FROM Installation i WHERE i.dvHubId = ?1")
    Installation findByDVHubId(String dvHubId);

}
