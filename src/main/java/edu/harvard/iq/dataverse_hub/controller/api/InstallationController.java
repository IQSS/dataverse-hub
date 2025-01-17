package edu.harvard.iq.dataverse_hub.controller.api;


import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.harvard.iq.dataverse_hub.controller.api.annotations.InstallationControllerDocs;
import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationFilterParams;
import edu.harvard.iq.dataverse_hub.controller.api.request.InstallationFilterParamsMonthly;
import edu.harvard.iq.dataverse_hub.controller.api.response.InstallationsByCountry;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationMetrics;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;
import edu.harvard.iq.dataverse_hub.model.DTO.InstallationDTO;
import edu.harvard.iq.dataverse_hub.model.DTO.MetricsByInstallationDTO;
import edu.harvard.iq.dataverse_hub.service.InstallationService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/installation")
public class InstallationController {

    @Autowired
    private InstallationService installationService;

    @GetMapping()
    @InstallationControllerDocs.GetInstallations
    public List<InstallationDTO> getInstallations(){
        List<Installation> installations = installationService.findAll();
        List<InstallationDTO> installationDTOs = new ArrayList<InstallationDTO>();
        for(Installation installation : installations){
            installationDTOs.add(new InstallationDTO(installation));
        }
        return installationDTOs;
    }
    
    @PutMapping
    @InstallationControllerDocs.CreateInstallation
    public Installation createInstallation(Installation installation){
        return installationService.save(installation);
    }

    @GetMapping("status")
    @InstallationControllerDocs.GetInstallationsStatus
    public List<InstallationVersionInfo> geInstallationsStatus(){
        return installationService.getInstallationInfo();
    }

    @GetMapping("country")
    @InstallationControllerDocs.getInstallationsByCountry
    public List<InstallationsByCountry> getInstallationsByCountry(){
        return installationService.getInstallationsByCountry();
    }

    @GetMapping("metrics")
    @InstallationControllerDocs.getInstallationsMetrics
    public List<MetricsByInstallationDTO> getInstallationsMetrics(@ParameterObject InstallationFilterParams installationFilterParams){

        InstallationFilterParamsMonthly installationFilterParamsMonthly = new InstallationFilterParamsMonthly();    
        installationFilterParamsMonthly.setDvHubId(installationFilterParams.getDvHubId());
        installationFilterParamsMonthly.setInstallationName(installationFilterParams.getInstallationName());
        installationFilterParamsMonthly.setCountry(installationFilterParams.getCountry());
        installationFilterParamsMonthly.setContinent(installationFilterParams.getContinent());
        installationFilterParamsMonthly.setLaunchYear(installationFilterParams.getLaunchYear());
        installationFilterParamsMonthly.setGdccMember(installationFilterParams.getGdccMember());
        installationFilterParamsMonthly.setMaxFiles(installationFilterParams.getMaxFiles());
        installationFilterParamsMonthly.setMinFiles(installationFilterParams.getMinFiles());
        installationFilterParamsMonthly.setMaxDatasets(installationFilterParams.getMaxDatasets());
        installationFilterParamsMonthly.setMinDatasets(installationFilterParams.getMinDatasets());
        installationFilterParamsMonthly.setMaxDataverses(installationFilterParams.getMaxDataverses());
        installationFilterParamsMonthly.setMinDataverses(installationFilterParams.getMinDataverses());
        installationFilterParamsMonthly.setMaxHarvested(installationFilterParams.getMaxHarvested());
        installationFilterParamsMonthly.setMinHarvested(installationFilterParams.getMinHarvested());
        installationFilterParamsMonthly.setMaxLocalDatasets(installationFilterParams.getMaxLocalDatasets());
        installationFilterParamsMonthly.setMinLocalDatasets(installationFilterParams.getMinLocalDatasets());
        
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        installationFilterParamsMonthly.setFromDate(currentDate);
        
        List<Installation> installations = installationService.installationMetricsByMonth(installationFilterParamsMonthly);

        List<MetricsByInstallationDTO> metricsByInstallationDTOs = new ArrayList<MetricsByInstallationDTO>();
        for(Installation installation : installations){
            metricsByInstallationDTOs.add(new MetricsByInstallationDTO(installation));
        }
        
        return metricsByInstallationDTOs;
    }

    @GetMapping("metrics/monthly")
    @InstallationControllerDocs.getMonthlyInstallationsMetrics
    public List<MetricsByInstallationDTO> getMonthlyInstallationsMetrics(@ParameterObject InstallationFilterParamsMonthly installationFilterParams){
        
        List<Installation> installations = installationService.installationMetricsByMonth(installationFilterParams);

        List<MetricsByInstallationDTO> metricsByInstallationDTOs = new ArrayList<MetricsByInstallationDTO>();
        for(Installation installation : installations){
            metricsByInstallationDTOs.add(new MetricsByInstallationDTO(installation));
        }
        
        return metricsByInstallationDTOs;
    }


}
