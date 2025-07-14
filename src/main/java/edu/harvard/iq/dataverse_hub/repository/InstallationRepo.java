package edu.harvard.iq.dataverse_hub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationFilterParamsMonthly;
import edu.harvard.iq.dataverse_hub.controller.api.response.InstallationsByCountry;
import edu.harvard.iq.dataverse_hub.model.Installation;


public interface InstallationRepo extends JpaRepository<Installation, String> {
    
    @Query("SELECT i FROM Installation i WHERE LOWER(i.hostname) = LOWER(?1)")
    Installation findByHostname(String hostname);

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
            ON i.hostname = ivi.installation.hostname
            WHERE ivi.status = 'OK'
            AND ivi.recordDate = (
                SELECT MAX(ivi_sub.recordDate) 
                FROM InstallationVersionInfo ivi_sub 
                WHERE ivi_sub.installation.hostname = ivi.installation.hostname
            )   
            """)
    List<Installation> getHealthyInstallations();

    @Query("""
        SELECT i FROM Installation i
        JOIN FETCH i.metrics im
        WHERE im.recordDate = (
            SELECT MAX(im_sub.recordDate)
            FROM InstallationMetrics im_sub
            WHERE im_sub.installation.hostname = i.hostname
            AND EXTRACT(YEAR FROM im_sub.recordDate) = EXTRACT(YEAR FROM im.recordDate)
            AND EXTRACT(MONTH FROM im_sub.recordDate) = EXTRACT(MONTH FROM im.recordDate)
        )
        AND (:#{#params.hostname} IS NULL OR i.hostname = :#{#params.hostname})
        AND (:#{#params.installationName} IS NULL OR LOWER(i.name) LIKE LOWER(CONCAT('%', :#{#params.installationName}, '%')))
        AND (:#{#params.country} IS NULL OR i.country = :#{#params.country})
        AND (:#{#params.launchYear} IS NULL OR i.launchYear = :#{#params.launchYear})
        AND (:#{#params.continent} IS NULL OR i.continent = :#{#params.continent})
        AND (:#{#params.gdccMember} IS NULL OR i.gdccMember = :#{#params.gdccMember})
        AND (:#{#params.active} IS NULL OR i.active = :#{#params.active})
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
        AND (:#{#params.fromDate} IS NULL OR im.recordDate >= TO_DATE(:#{#params.fromDate}, 'YYYY-MM'))
        AND (:#{#params.toDate} IS NULL OR im.recordDate <= TO_DATE(:#{#params.toDate}, 'YYYY-MM'))        
        ORDER BY im.recordDate ASC
        """)
    List<Installation> installationMetricsByMonth(InstallationFilterParamsMonthly params);

    @Query("""
            SELECT i FROM Installation i
            WHERE i.active = true
            AND i.hostname NOT IN (
                SELECT ivi.installation.hostname
                FROM InstallationVersionInfo ivi
                WHERE ivi.status = 'OK'
                AND ivi.recordDate = (
                    SELECT MAX(ivi_sub.recordDate)
                    FROM InstallationVersionInfo ivi_sub
                    WHERE ivi_sub.installation.hostname = ivi.installation.hostname
                )
            )
            """)
    List<Installation> getMissingInstallations(List<Installation> installation);
}
