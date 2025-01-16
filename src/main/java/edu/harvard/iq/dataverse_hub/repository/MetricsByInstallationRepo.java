package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationFilterParamsMonthly;
import edu.harvard.iq.dataverse_hub.model.MetricsByInstallation;

@Repository
public interface MetricsByInstallationRepo extends JpaRepository<MetricsByInstallation, String>{

    @Query("""
        SELECT mbi FROM MetricsByInstallation mbi
        WHERE (:#{#params.dvHubId} IS NULL OR mbi.dvHubId = :#{#params.dvHubId})
        AND (:#{#params.installationName} IS NULL OR UPPER(mbi.name) LIKE %:#{#params.installationName == null ? null : #params.installationName.toUpperCase()}%)
        """)
    public List<MetricsByInstallation> findMonthly(@RequestParam InstallationFilterParamsMonthly params);
    /**
     * 
        WHERE element(mbi.metrics).recordDate = (
            SELECT MAX(element(mbi_s.metrics).recordDate) 
            FROM MetricsByInstallation mbi_s 
            WHERE mbi_s.dvHubId = mbi.dvHubId
            AND MONTH(element(mbi_s.metrics).recordDate) = MONTH(element(mbi.metrics).recordDate)
            AND YEAR(element(mbi_s.metrics).recordDate) = YEAR(element(mbi.metrics).recordDate)
            GROUP BY YEAR(element(mbi_s.metrics).recordDate), MONTH(element(mbi_s.metrics).recordDate)
        )
        AND mbi.country = :#{#params.country}
        AND LOWER(mbi.name) like LOWER(:#{#params.installationName})
        ORDER BY element(mbi.metrics).recordDate DESC
     */
}
