package edu.harvard.iq.dataverse_hub.controller.scheduled;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;
import edu.harvard.iq.dataverse_hub.service.InstallationService;
import edu.harvard.iq.dataverse_hub.service.RestUtilService;
import edu.harvard.iq.dataverse_hub.service.ScheduledJobService;

@Component
public class VersionDVInstallationCheck {

    @Autowired
    private InstallationService installationService;

    @Autowired
    private ScheduledJobService scheduledJobService;

    @Autowired
    private RestUtilService restUtilService;

    private final String JOB_NAME = this.getClass().getSimpleName();

    @Scheduled(fixedRate = 60000)
    public List<InstallationVersionInfo> runTask(){
        List<InstallationVersionInfo> versionInfoList = null;
        try { 
            versionInfoList = startTask(null);
        } catch (Exception e) { 
            versionInfoList = null;
        }
        return versionInfoList;
    }

    /**
     * @param isDue
     * @return
     */
    public List<InstallationVersionInfo> startTask(Boolean isDue) throws JsonProcessingException {

        if(isDue == null){
            isDue = scheduledJobService.isDue(JOB_NAME);
        }
        return isDue ? importInstallationsStatus(null) : null;
    }

    
    public List<InstallationVersionInfo> importInstallationsStatus(List<Installation> dvInstallationsList) {
        
        List<InstallationVersionInfo> versionInfoList = null;
        try {

            if(dvInstallationsList == null){
                dvInstallationsList = installationService.findAll();
            }

            versionInfoList = new ArrayList<InstallationVersionInfo>();

            for (Installation installation : dvInstallationsList) {
                versionInfoList.add(getVersionInfo(installation));
            }

            installationService.saveAllVersionInfo(versionInfoList);
            scheduledJobService.saveTransactionLog(JOB_NAME, 1);
        } catch (Exception e) {
            scheduledJobService.saveTransactionLog(JOB_NAME, -1);
        }

        return versionInfoList;        
    }

    public InstallationVersionInfo getVersionInfo(Installation installation){
        try {
            String url = "https://" + installation.getHostname() + "/api/info/version";
            VersionInfo jsonImport = restUtilService.retrieveRestAPIObject(url, VersionInfo.class);
            return installationService.getLogInstallationVersion(jsonImport, installation);                   
        } catch (Exception e) {
            return installationService.getLogInstallationVersion(null, installation, e.getClass().getSimpleName());                    
        }       
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VersionInfo {
        @JsonProperty("status")
        public String status;
        
        @JsonProperty("data")
        public VersionData data;

        public void setStatus(String status) {
            this.status = status;
        }

        public void setVersionData(VersionData data) {
            this.data = data;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VersionData {

        @JsonProperty("version")
        public String version;

        @JsonProperty("build")
        public String build;

        public void setVersion(String version) {
            this.version = version;
        }

        public void setBuild(String build) {
            this.build = build;
        }
    }


}
