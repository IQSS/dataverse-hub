package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationFilterParams;
import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationFilterParamsMonthly;
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
            WHERE (:#{#params.dvHubId} IS NULL OR im.installation.dvHubId = :#{#params.dvHubId})
            AND (:#{#params.installationName} IS NULL OR UPPER(im.installation.name) LIKE %:#{#params.installationName == null ? null : #params.installationName.toUpperCase()}%)
            AND (:#{#params.country} IS NULL OR im.installation.country = :#{#params.country})
            AND (:#{#params.launchYear} IS NULL OR im.installation.launchYear = :#{#params.launchYear})
            AND (:#{#params.continent} IS NULL OR im.installation.continent = :#{#params.continent})
            AND (:#{#params.gdccMember} IS NULL OR im.installation.gdccMember = :#{#params.gdccMember})
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
            ORDER BY im.installation.dvHubId, im.recordDate
            """)
    public List<InstallationMetrics> findLatest(@RequestParam InstallationFilterParams params);

    @Query("""
            SELECT im FROM InstallationMetrics im
            RIGHT JOIN FETCH InstallationMetrics im_r
            ON im.installation.dvHubId = im.installation.dvHubId 
            AND im.recordDate = (
                SELECT MAX(im_s.recordDate) 
                FROM InstallationMetrics im_s 
                WHERE im_s.installation.dvHubId = im.installation.dvHubId
                AND date_trunc('month', im_s.recordDate) = date_trunc('month', im.recordDate)
            )
            WHERE (:#{#params.dvHubId} IS NULL OR im.installation.dvHubId = :#{#params.dvHubId})
            AND (:#{#params.installationName} IS NULL OR UPPER(im.installation.name) LIKE %:#{#params.installationName == null ? null : #params.installationName.toUpperCase()}%)
            AND (:#{#params.country} IS NULL OR im.installation.country = :#{#params.country})
            AND (:#{#params.launchYear} IS NULL OR im.installation.launchYear = :#{#params.launchYear})
            AND (:#{#params.continent} IS NULL OR im.installation.continent = :#{#params.continent})
            AND (:#{#params.gdccMember} IS NULL OR im.installation.gdccMember = :#{#params.gdccMember})
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
            AND (:#{#params.fromDate} IS NULL OR im.recordDate >= CAST(:#{#params.fromDate} AS java.sql.Timestamp))
            AND (:#{#params.toDate} IS NULL OR im.recordDate <= CAST(:#{#params.toDate} AS java.sql.Timestamp))
            ORDER BY im.installation.dvHubId, im.recordDate
            """)
    public List<InstallationMetrics> findMonthly(@RequestParam InstallationFilterParamsMonthly params);

}
