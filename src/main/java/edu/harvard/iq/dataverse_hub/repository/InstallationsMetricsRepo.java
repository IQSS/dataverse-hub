package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

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
            AND (:continent IS NULL OR im.installation.continent = :continent)
            AND (:gdccMember IS NULL OR im.installation.gdccMember = :gdccMember)
            AND (:maxFiles IS NULL OR im.files <= :maxFiles)
            AND (:minFiles IS NULL OR im.files >= :minFiles)
            AND (:maxDatasets IS NULL OR im.datasets <= :maxDatasets)
            AND (:minDatasets IS NULL OR im.datasets >= :minDatasets)
            AND (:maxDataverses IS NULL OR im.dataverses <= :maxDataverses)
            AND (:minDataverses IS NULL OR im.dataverses >= :minDataverses)
            AND (:maxHarvested IS NULL OR im.harvestedDatasets <= :maxHarvested)
            AND (:minHarvested IS NULL OR im.harvestedDatasets >= :minHarvested)
            AND (:minLocalDatasets IS NULL OR im.localDatasets >= :minLocalDatasets)
            AND (:maxLocalDatasets IS NULL OR im.localDatasets <= :maxLocalDatasets)
            """)
    public List<InstallationMetrics> findLatest(String country,
                                                String continent,
                                                Integer launchYear,
                                                Boolean gdccMember,
                                                Integer maxFiles,
                                                Integer minFiles,
                                                Integer maxDatasets,
                                                Integer minDatasets,
                                                Integer maxDataverses,
                                                Integer minDataverses,
                                                Integer maxHarvested,
                                                Integer minHarvested,
                                                Integer maxLocalDatasets,
                                                Integer minLocalDatasets);

}
