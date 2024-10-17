package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;

public interface InstallationVersionInfoRepo extends JpaRepository<InstallationVersionInfo, Integer> {

    

}
