package edu.harvard.iq.dataverse_hub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationFilterParams;
import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationFilterParamsMonthly;
import edu.harvard.iq.dataverse_hub.controller.api.response.InstallationsByCountry;
import edu.harvard.iq.dataverse_hub.controller.scheduled.VersionDVInstallationCheck;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationMetrics;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;
import edu.harvard.iq.dataverse_hub.repository.InstallationRepo;
import edu.harvard.iq.dataverse_hub.repository.InstallationVersionInfoRepo;
import edu.harvard.iq.dataverse_hub.repository.InstallationsMetricsRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InstallationService {

    @Autowired
    private InstallationRepo installationRepo;

    @Autowired
    private InstallationVersionInfoRepo installationVersionInfoRepo;

    @Autowired
    private InstallationsMetricsRepo installationsMetricsRepo;


    /**
     * Find an installation by its id
     * @param id
     * @return
     */
    public Optional<Installation> findById(String id) {
        return installationRepo.findById(id);
    } 

    /**
     * Save an installation
     * @param installation
     * @return
     */
    public Installation save(Installation installation) {
        return installationRepo.save(installation);
    }

    /**
     * Find all installations
     * @return
     */
    public List<Installation> findAll() {
        return installationRepo.findAll();
    }

    /**
     * Get the latest status of all installations
     * @return
     */
    @Cacheable(value = "installationsStatus", key = "#root.method.name")
    public List<InstallationVersionInfo> getInstallationInfo(){
        List<InstallationVersionInfo> installationsStatus = installationVersionInfoRepo.getLatestStatusAll();
        return installationsStatus;
    }

    /**
     * Log the installation version information
     * @param info
     * @param installation
     * @return
     */
    public InstallationVersionInfo getLogInstallationVersion(
                    VersionDVInstallationCheck.VersionInfo info, 
                    Installation installation){
        return getLogInstallationVersion(info, installation, null);
    }

    /**
     * Log the installation version information
     * @param info
     * @param installation
     * @param code
     * @return
     */
    public InstallationVersionInfo getLogInstallationVersion(
                    VersionDVInstallationCheck.VersionInfo info,
                    Installation installation, 
                    String code){

        InstallationVersionInfo installationVersionInfo = new InstallationVersionInfo();
        installationVersionInfo.setInstallation(installation);
        installationVersionInfo.setRecordDate(new Date());
        if(info == null){
            installationVersionInfo.setStatus(code);
        } else {
            installationVersionInfo.setStatus(info.status);
            installationVersionInfo.setVersion(info.data.version);
            installationVersionInfo.setBuild(info.data.build);
        }
        
        return installationVersionInfo;
    }

    /**
     * 
     * @param installationList
     * @return
     */
    public List<Installation> saveAllInstallations(List<Installation> installationList){
        return installationRepo.saveAll(installationList);
    }

    /**
     * 
     * @param versionInfoList
     * @return
     */
    public List<InstallationVersionInfo> saveAllVersionInfo(List<InstallationVersionInfo> versionInfoList){
        return installationVersionInfoRepo.saveAll(versionInfoList);
    }

    /**
     * Retrieve all the registered installations by country ignoring the status
     * @return
     */
    public List<InstallationsByCountry> getInstallationsByCountry(){
        return installationRepo.getInstallationsByCountry();
    }

    /**
     * Retrieve all the installations that are healthy
     */
    public List<Installation> getHealtyInstallations() {
        return installationRepo.getHealthyInstallations();
    }

    /**
     * retrieve the most recent metrics from all installations
     * @return
     */
    public List<InstallationMetrics> getInstallationMetrics(InstallationFilterParams installationFilterParams){
        return installationsMetricsRepo.findLatest(installationFilterParams);
    }

    /**
     * Save all the metrics
     * @param metricsList
     * @return
     */
    public List<InstallationMetrics> saveAllMetrics(List<InstallationMetrics> metricsList) {
        return installationsMetricsRepo.saveAll(metricsList);
    }

    /**
     * Retrieve the metrics for all installations by month
     * @param installationFilterParams
     * @return
     */
    @Cacheable(value = "installationsMetricsMonthly", key = "#root.method.name + '_' + #installationFilterParams.hashCode()")
    public List<Installation> installationMetricsByMonth(InstallationFilterParamsMonthly installationFilterParams){
        return installationRepo.installationMetricsByMonth(installationFilterParams);
    }

}
