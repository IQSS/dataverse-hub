package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.harvard.iq.dataverse_hub.model.DevMetricsReleases;

public interface DevMetricsReleasesRepo extends JpaRepository<DevMetricsReleases, String> {
}
