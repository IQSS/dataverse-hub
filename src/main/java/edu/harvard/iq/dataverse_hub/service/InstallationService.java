package edu.harvard.iq.dataverse_hub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.harvard.iq.dataverse_hub.controller.api.payloadBeans.InstallationsByCountry;
import edu.harvard.iq.dataverse_hub.controller.scheduled.VersionDVInstallationCheck;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;
import edu.harvard.iq.dataverse_hub.repository.InstallationRepo;
import edu.harvard.iq.dataverse_hub.repository.InstallationVersionInfoRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InstallationService {

    @Autowired
    private InstallationRepo installationRepo;

    @Autowired
    private InstallationVersionInfoRepo installationVersionInfoRepo;

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
    public List<InstallationVersionInfo> getInstallationInfo(){
        return installationVersionInfoRepo.getLatestStatusAll();
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
        installationVersionInfo.setCaptureDate(new Date());
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

    public List<InstallationsByCountry> getInstallationsByCountry(){
        return installationRepo.getInstallationsByCountry();
    }

}
