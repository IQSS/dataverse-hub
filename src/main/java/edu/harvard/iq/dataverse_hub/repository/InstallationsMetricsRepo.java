package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.stereotype.Repository;

import edu.harvard.iq.dataverse_hub.model.InstallationMetrics;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface InstallationsMetricsRepo extends JpaRepository<InstallationMetrics, Integer>{

    @Query("""
            SELECT im FROM InstallationMetrics im
            RIGHT JOIN FETCH InstallationMetrics im_r
            ON im.installation.dvHubId = im.installation.dvHubId 
            AND im.recordDate = (
                SELECT MAX(im_s.recordDate) 
                FROM InstallationMetrics im_s 
                WHERE im_s.installation.dvHubId = im.installation.dvHubId
            )
            WHERE (:country IS NULL OR im.installation.country = :country)
            AND (:launchYear IS NULL OR im.installation.launchYear = :launchYear)
            """)
    public List<InstallationMetrics> findLatest(String country, Integer launchYear);

}
