package edu.harvard.iq.dataverse_hub.controller.scheduled;

import java.util.ArrayList;
import java.util.List;

import javax.cache.CacheManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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

    Logger logger = LoggerFactory.getLogger(InstallationGitImporter.class);

    @Autowired
    private InstallationService installationService;

    @Autowired
    private ScheduledJobService scheduledJobService;

    @Autowired
    private RestUtilService restUtilService;

    @Autowired
    private CacheManager cacheManager;

    private final String JOB_NAME = this.getClass().getSimpleName();
    private final String PROTOCOL = "https://";
    private final String ENDPOINT = "/api/info/version";


    @Scheduled(fixedRate = 3600000)
    public List<InstallationVersionInfo> runTask(){
        logger.info("Starting {} job", JOB_NAME);

        try {
            List<InstallationVersionInfo> versionInfo = startTask(null);
            logger.info("Job {} successfully completed", JOB_NAME);
            return versionInfo;
        } catch (Exception e) {
            logger.error("Problem running job {}", JOB_NAME, e);
            return null;
        }
    }

    public List<InstallationVersionInfo> startTask(Boolean isDue) throws JsonProcessingException {

        if(isDue == null){
            logger.info("Checking if job is due");
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

            if(dvInstallationsList.isEmpty()){
                scheduledJobService.saveTransactionLog(JOB_NAME, 0);
                return versionInfoList;
            }

            versionInfoList = new ArrayList<InstallationVersionInfo>();

            for (Installation installation : dvInstallationsList) {
                logger.info("Checking version for installation: " + installation.getHostname());
                versionInfoList.add(getVersionInfo(installation));
            }

            installationService.saveAllVersionInfo(versionInfoList);
            scheduledJobService.saveTransactionLog(JOB_NAME, 1);
            clearCache();
            installationService.getInstallationInfo();
        } catch (Exception e) {
            scheduledJobService.saveTransactionLog(JOB_NAME, -1);
        }

        return versionInfoList;        
    }

    public InstallationVersionInfo getVersionInfo(Installation installation){
        try {
            String url = PROTOCOL + installation.getHostname() + ENDPOINT;
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

    private void clearCache(){
        cacheManager.getCache("installationsStatus").clear();
        logger.info("Clearing cache for " + JOB_NAME);
    }


}
