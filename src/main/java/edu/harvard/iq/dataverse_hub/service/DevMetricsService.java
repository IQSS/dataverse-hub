package edu.harvard.iq.dataverse_hub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.harvard.iq.dataverse_hub.model.DevMetrics;
import edu.harvard.iq.dataverse_hub.model.DevMetricsReleases;
import edu.harvard.iq.dataverse_hub.repository.DevMetricsReleasesRepo;
import edu.harvard.iq.dataverse_hub.repository.DevMetricsRepo;

@Service
public class DevMetricsService {

    @Autowired
    private DevMetricsReleasesRepo devMetricsReleasesRepo;

    @Autowired
    private DevMetricsRepo devMetricsRepo;

    public List<DevMetrics> SaveAllDevMetrics(List<DevMetrics> devMetrics) {
        return devMetricsRepo.saveAll(devMetrics);
    }

    public DevMetrics getLatestDevMetrics() {
        return devMetricsRepo.getLatestDevMetrics();
    }

    public DevMetrics saveDevMetrics(DevMetrics devMetrics) {
        return devMetricsRepo.save(devMetrics);
    }

    public List<DevMetricsReleases> saveAllDevMetricsReleases(List<DevMetricsReleases> devMetricsReleases) {
        return devMetricsReleasesRepo.saveAll(devMetricsReleases);
    }

    public List<DevMetricsReleases> getAllDevMetricsReleases() {
        return devMetricsReleasesRepo.findAll();
    }

}
