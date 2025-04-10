package edu.harvard.iq.dataverse_hub.controller.scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.cache.CacheManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationFilterParamsMonthly;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationMetrics;
import edu.harvard.iq.dataverse_hub.service.InstallationService;
import edu.harvard.iq.dataverse_hub.service.RestUtilService;
import edu.harvard.iq.dataverse_hub.service.ScheduledJobService;

@Component
public class InstallationMetricsImporter {

    Logger logger = LoggerFactory.getLogger(InstallationGitImporter.class);

    public final String PROTOCOL = "https://";

    public final String DATASETS_ENDPOINT = "/api/info/metrics/datasets";
    public final String DATASETS_HARVESTED_ENDPOINT = "/api/info/metrics/datasets?dataLocation=remote";
    public final String DATASETS_LOCAL_ENDPOINT = "/api/info/metrics/datasets?dataLocation=local";
    public final String FILES_ENDPOINT = "/api/info/metrics/files";
    public final String DOWNLOADS_ENDPOINT = "/api/info/metrics/downloads";
    public final String DATAVERSES_ENDPOINT = "/api/info/metrics/dataverses";
    
    private final String JOB_NAME = this.getClass().getSimpleName();

    @Autowired
    private InstallationService installationService;

    @Autowired
    private ScheduledJobService scheduledJobService;

    @Autowired
    private RestUtilService restUtilService;

    @Autowired
    private CacheManager cacheManager;


    @Scheduled(fixedRate = 21600000)
    public List<InstallationMetrics> runTask() {
        logger.info("Starting {} job", JOB_NAME);
        try {
            List<InstallationMetrics> installations = startTask(null);
            logger.info("Job {} successfully completed", JOB_NAME);
            return installations;
        } catch (Exception e) {
            logger.error("Problem running job {}", JOB_NAME, e);
            return null;
        }
    }
    
    /**
     * @param isDue will start the task depending on if is due or not.
     * @return the list of installations added.
     */
    public List<InstallationMetrics> startTask(Boolean isDue) throws JsonProcessingException {

        if(isDue == null){
            isDue = scheduledJobService.isDue(JOB_NAME);
        }

        return isDue ? importInstallationsMetrics(null) : null;
    }

    //@CacheEvict(value = "installationsMetricsMonthly", allEntries = true)
    public List<InstallationMetrics> importInstallationsMetrics(List<Installation> dvInstallationsList) {
        List<InstallationMetrics> metricsList = null;

        try {
            if(dvInstallationsList == null){
                dvInstallationsList = installationService.getHealtyInstallations();

                /**
                 * This case would only happen when there are no installations in the database that are responding.
                 */
                if(dvInstallationsList == null || dvInstallationsList.size() == 0){
                    scheduledJobService.saveTransactionLog(JOB_NAME, 0);
                    return null;
                }
            }

            metricsList = new ArrayList<InstallationMetrics>();

            for(Installation installation : dvInstallationsList){
                InstallationMetrics metrics = getInstallationMetrics(installation);
                if(metrics != null){
                    metricsList.add(metrics);
                }
            }

            installationService.saveAllMetrics(metricsList);
            scheduledJobService.saveTransactionLog(JOB_NAME, 1);
            //Call to the api to cache the metrics by month
            installationService.installationMetricsByMonth(new InstallationFilterParamsMonthly());
            
        } catch (Exception e) {
            scheduledJobService.saveTransactionLog(JOB_NAME, -1);
            return null;
        }

        return metricsList;
    }

    public InstallationMetrics getInstallationMetrics(Installation installation){

        logger.info("Checking metrics for installation: " + installation.getHostname());
        try {
            MetricInfo datasets = restUtilService.retrieveRestAPIObject(PROTOCOL + installation.getHostname() + DATASETS_ENDPOINT, MetricInfo.class);    
            MetricInfo harvested = restUtilService.retrieveRestAPIObject(PROTOCOL + installation.getHostname() + DATASETS_HARVESTED_ENDPOINT, MetricInfo.class);    
            MetricInfo local = restUtilService.retrieveRestAPIObject(PROTOCOL + installation.getHostname() + DATASETS_LOCAL_ENDPOINT, MetricInfo.class);    
            MetricInfo files = restUtilService.retrieveRestAPIObject(PROTOCOL + installation.getHostname() + FILES_ENDPOINT, MetricInfo.class);    
            MetricInfo downloads = restUtilService.retrieveRestAPIObject(PROTOCOL + installation.getHostname() + DOWNLOADS_ENDPOINT, MetricInfo.class);    
            MetricInfo dataverses = restUtilService.retrieveRestAPIObject(PROTOCOL + installation.getHostname() + DATAVERSES_ENDPOINT, MetricInfo.class);    

            InstallationMetrics metrics = new InstallationMetrics();
            metrics.setInstallation(installation);
            metrics.setRecordDate(LocalDateTime.now());
            metrics.setDatasets(datasets.data.count.intValue());
            metrics.setHarvestedDatasets(harvested.data.count.intValue());
            metrics.setLocalDatasets(local.data.count.intValue());
            metrics.setFiles(files.data.count);
            metrics.setDownloads(downloads.data.count);
            metrics.setDataverses(dataverses.data.count.intValue());

            return metrics;

        } catch (Exception e) {
            logger.error("Problem running job {} for {}", JOB_NAME, installation.getDvHubId(), e);
            return null;
        }
    
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MetricInfo {
        @JsonProperty("status")
        public String status;
        
        @JsonProperty("data")
        public MetricData data;

        public void setStatus(String status) {
            this.status = status;
        }

        public void setMetricData(MetricData data) {
            this.data = data;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MetricData {

        @JsonProperty("count")
        public Long count;

        public void setCount(Long count) {
            this.count = count;
        }
    }

    private void clearCache(){
        cacheManager.getCache("installationsMetricsMonthly").clear();
        logger.info("Clearing cache for " + JOB_NAME);
    }

  

}
