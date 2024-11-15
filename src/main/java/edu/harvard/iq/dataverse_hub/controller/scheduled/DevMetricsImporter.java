package edu.harvard.iq.dataverse_hub.controller.scheduled;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.harvard.iq.dataverse_hub.model.DevMetrics;
import edu.harvard.iq.dataverse_hub.model.DevMetricsReleases;
import edu.harvard.iq.dataverse_hub.service.DevMetricsService;
import edu.harvard.iq.dataverse_hub.service.RestUtilService;
import edu.harvard.iq.dataverse_hub.service.ScheduledJobService;

@Component
public class DevMetricsImporter {

    Logger logger = LoggerFactory.getLogger(InstallationGitImporter.class);

    private final String JOB_NAME = this.getClass().getSimpleName();
    public static final String DEV_METRICS_URL = "https://api.github.com/repos/IQSS/dataverse";
    public static final String RELEASES_METRICS_URL = "https://api.github.com/repos/IQSS/dataverse/releases";

    @Autowired
    private RestUtilService restUtilService;

    @Autowired
    private ScheduledJobService scheduledJobService;

    @Autowired
    private DevMetricsService devMetricsService;

    @Scheduled(fixedRate = 21600000)
    public Boolean runTask() {
        logger.info("Starting {} job", JOB_NAME);
        try {
            Boolean metricsSuccess = startTask(null);
            logger.info("Job {} successfully completed", JOB_NAME);
            return metricsSuccess;
        } catch (Exception e) {
            logger.error("Problem running job {}", JOB_NAME, e);
            return null;
        }
    }

    /**
     * @param isDue will start the task depending on if is due or not.
     * @return the list of installations added.
     */
    public Boolean startTask(Boolean isDue) throws JsonProcessingException {

        if(isDue == null){
            isDue = scheduledJobService.isDue(JOB_NAME);
        }
        return isDue ? importDevMetrics() : null;
    }

    /**
     * @param url the url of the json file to be used to import of the dev metrics.
     * @return the list of metrics extracted from the JSON file and saved on the database.
     */
    public Boolean importDevMetrics() throws JsonProcessingException {
        try {
            importReleaseMetrics(
                importdDevMetrics().getRepoName()
            );
            
            scheduledJobService.saveTransactionLog(JOB_NAME, 1);
            return true;
        } catch (Exception e) {
            scheduledJobService.saveTransactionLog(JOB_NAME, -1);
            return false;
        }

    }

    public DevMetrics importdDevMetrics() throws JsonProcessingException {
        DevMetrics devMetrics = restUtilService.retrieveRestAPIObject(DEV_METRICS_URL, DevMetrics.class);
        devMetrics.setRecordDate(new Date());
        return  devMetricsService.saveDevMetrics(devMetrics);
    }

    public List<DevMetricsReleases> importReleaseMetrics(String repoName) throws JsonProcessingException {
        List<DevMetricsReleases> releasesMetrics = List.of(
            restUtilService.retrieveRestAPIObject(RELEASES_METRICS_URL, DevMetricsReleases[].class));
        for (DevMetricsReleases devMetricsReleases : releasesMetrics) {
            devMetricsReleases.setRepoName(repoName);
        }
        return devMetricsService.saveAllDevMetricsReleases(releasesMetrics);       
    }





}
