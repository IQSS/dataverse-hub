package edu.harvard.iq.dataverse_hub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Installation findByDVHubId(String name) {
        return installationRepo.findByDVHubId(name);
    }

    public Optional<Installation> findById(String id) {
        return installationRepo.findById(id);
    } 

    public Installation save(Installation installation) {
        return installationRepo.save(installation);
    }

    public List<Installation> findAll() {
        return installationRepo.findAll();
    }

    public List<InstallationVersionInfo> getInstallationInfo(){
        return installationVersionInfoRepo.getLatestStatusAll();
    }

    public InstallationVersionInfo logInstallationVersion(
                    VersionDVInstallationCheck.VersionInfo info, 
                    Installation installation){
        return logInstallationVersion(info, installation, null);
    }

    public InstallationVersionInfo logInstallationVersion(
                    VersionDVInstallationCheck.VersionInfo info, 
                    Installation installation, 
                    String code){

        InstallationVersionInfo vi = new InstallationVersionInfo();    
        vi.setInstallation(installation);
        vi.setCaptureDate(new Date());
        if(info == null){
            vi.setStatus(code);
        } else {
            vi.setStatus(info.status);
            vi.setVersion(info.data.version);
            vi.setBuild(info.data.build);
        }
        
        return installationVersionInfoRepo.save(vi);
    }

}
