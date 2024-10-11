package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.harvard.iq.dataverse_hub.model.Installation;


public interface InstallationRepo extends JpaRepository<Installation, String> {
    
    @Query("SELECT i FROM Installation i WHERE i.name = ?1")
    Installation findByName(String name);

}
