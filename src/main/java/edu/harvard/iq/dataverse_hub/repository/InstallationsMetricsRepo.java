package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationMetricsFilterParams;
import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationFilterParamsMonthly;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationMetrics;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface InstallationsMetricsRepo extends JpaRepository<InstallationMetrics, Integer>{

    @Query("""
            SELECT im FROM InstallationMetrics im
            RIGHT JOIN FETCH InstallationMetrics im_r
            ON im.installation.hostname = im.installation.hostname
            AND im.recordDate = (
                SELECT MAX(im_s.recordDate) 
                FROM InstallationMetrics im_s 
                WHERE im_s.installation.hostname = im.installation.hostname
            )
            WHERE (:#{#params.hostname} IS NULL OR im.installation.hostname = :#{#params.hostname})
            AND (:#{#params.installationName} IS NULL OR UPPER(im.installation.name) LIKE %:#{#params.installationName == null ? null : #params.installationName.toUpperCase()}%)
            AND (:#{#params.country} IS NULL OR im.installation.country = :#{#params.country})
            AND (:#{#params.launchYear} IS NULL OR im.installation.launchYear = :#{#params.launchYear})
            AND (:#{#params.continent} IS NULL OR im.installation.continent = :#{#params.continent})
            AND (:#{#params.gdccMember} IS NULL OR im.installation.gdccMember = :#{#params.gdccMember})
            AND (:#{#params.active} IS NULL OR im.installation.active = :#{#params.active})
            AND (:#{#params.maxFiles} IS NULL OR im.files <= :#{#params.maxFiles})
            AND (:#{#params.minFiles} IS NULL OR im.files >= :#{#params.minFiles})
            AND (:#{#params.maxDatasets} IS NULL OR im.datasets <= :#{#params.maxDatasets})
            AND (:#{#params.minDatasets} IS NULL OR im.datasets >= :#{#params.minDatasets})
            AND (:#{#params.maxDataverses} IS NULL OR im.dataverses <= :#{#params.maxDataverses})
            AND (:#{#params.minDataverses} IS NULL OR im.dataverses >= :#{#params.minDataverses})
            AND (:#{#params.maxHarvested} IS NULL OR im.harvestedDatasets <= :#{#params.maxHarvested})
            AND (:#{#params.minHarvested} IS NULL OR im.harvestedDatasets >= :#{#params.minHarvested})
            AND (:#{#params.minLocalDatasets} IS NULL OR im.localDatasets >= :#{#params.minLocalDatasets})
            AND (:#{#params.maxLocalDatasets} IS NULL OR im.localDatasets <= :#{#params.maxLocalDatasets})
            ORDER BY im.installation.hostname, im.recordDate
            """)
    public List<InstallationMetrics> findLatest(@RequestParam InstallationMetricsFilterParams params);
   
}
