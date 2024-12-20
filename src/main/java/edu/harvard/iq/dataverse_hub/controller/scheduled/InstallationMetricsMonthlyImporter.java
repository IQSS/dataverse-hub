package edu.harvard.iq.dataverse_hub.controller.scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationMetrics;
import edu.harvard.iq.dataverse_hub.service.InstallationService;
import edu.harvard.iq.dataverse_hub.service.RestUtilService;
import edu.harvard.iq.dataverse_hub.service.ScheduledJobService;

@Component
public class InstallationMetricsMonthlyImporter {

    Logger logger = LoggerFactory.getLogger(InstallationMetricsMonthlyImporter.class);
    private final String JOB_NAME = this.getClass().getSimpleName();

    public final String PROTOCOL = "https://";

    String DATASETS_ENDPOINT = "/api/info/metrics/datasets/toMonth/%s";
    String DATASETS_HARVESTED_ENDPOINT = "/api/info/metrics/datasets/toMonth/%s?dataLocation=remote";
    String DATASETS_LOCAL_ENDPOINT = "/api/info/metrics/datasets/toMonth/%s?dataLocation=local";
    String FILES_ENDPOINT = "/api/info/metrics/files/toMonth/%s";
    String DOWNLOADS_ENDPOINT = "/api/info/metrics/downloads/toMonth/%s";
    String DATAVERSES_ENDPOINT = "/api/info/metrics/dataverses/toMonth/%s";

    @Autowired
    private InstallationService installationService;

    @Autowired
    private ScheduledJobService scheduledJobService;

    @Autowired
    private RestUtilService restUtilService;

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

            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            int currentMonth = calendar.get(Calendar.MONTH) + 1;

            metricsList = new ArrayList<InstallationMetrics>();

            for(Installation installation : dvInstallationsList){

                Integer queryYear = installation.getLaunchYear();
                Integer queryMonth = 1;

                while(queryYear <= currentYear){

                    if(queryYear == currentYear && queryMonth == currentMonth){
                        break;
                    }
                    while(queryMonth <= 12){
                        InstallationMetrics metrics = getInstallationMetrics(installation, queryYear, queryMonth);
                        if(metrics != null){
                            metricsList.add(metrics);
                        }
                        queryMonth++;
                    }
                    queryMonth = 1;
                    queryYear++;
                }

            }

            installationService.saveAllMetrics(metricsList);            
            scheduledJobService.saveTransactionLog(JOB_NAME, 1);
            scheduledJobService.disableRecurrence(DATASETS_ENDPOINT);

        } catch (Exception e) {
            scheduledJobService.saveTransactionLog(JOB_NAME, -1);
            return null;
        }

        return metricsList;
    }

    public InstallationMetrics getInstallationMetrics(Installation installation, Integer year, Integer month){

        logger.info("Checking metrics for installation: " + installation.getHostname());
        String searchParam = year + "-" + (month < 10 ? "0" + month : month);
        try {

            MetricInfo datasets = retrieveMetrics(installation, DATASETS_ENDPOINT, searchParam);
            
            //If there are no datasets, we don't need to continue.
            if(datasets.data.count == 0){
                return null;
            }

            MetricInfo harvested = retrieveMetrics(installation, DATASETS_HARVESTED_ENDPOINT, searchParam);
            MetricInfo local = retrieveMetrics(installation, DATASETS_LOCAL_ENDPOINT, searchParam);
            MetricInfo files = retrieveMetrics(installation, FILES_ENDPOINT, searchParam);
            MetricInfo downloads = retrieveMetrics(installation, DOWNLOADS_ENDPOINT, searchParam);
            MetricInfo dataverses = retrieveMetrics(installation, DATAVERSES_ENDPOINT, searchParam);

            InstallationMetrics metrics = new InstallationMetrics();
            metrics.setInstallation(installation);

            //We use a "fake" date since we are importing older monhtly. 
            LocalDateTime localDateTime = LocalDateTime.of(year, month, 1, 0, 0);

            metrics.setRecordDate(localDateTime);
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

    private MetricInfo retrieveMetrics(Installation installation, String endpointUrl, String searchParam) throws JsonProcessingException {
        String metricsEndpoint = PROTOCOL + installation.getHostname() + String.format(endpointUrl, searchParam);
        MetricInfo metrics = restUtilService.retrieveRestAPIObject(metricsEndpoint,MetricInfo.class);
        return metrics;
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



}
