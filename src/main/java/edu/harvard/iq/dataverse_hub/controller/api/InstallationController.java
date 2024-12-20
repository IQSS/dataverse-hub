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
import edu.harvard.iq.dataverse_hub.service.InstallationService;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/installation")
public class InstallationController {

    @Autowired
    private InstallationService installationService;

    @GetMapping()
    @InstallationControllerDocs.GetInstallations
    public List<Installation> getInstallations(){
        return installationService.findAll();
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
    public List<InstallationMetrics> getInstallationsMetrics(@ParameterObject InstallationFilterParams installationFilterParams){
        return installationService.getInstallationMetrics(installationFilterParams);
    }

    @GetMapping("metrics/monthly")
    @InstallationControllerDocs.getMonthlyInstallationsMetrics
    public List<InstallationMetrics> getMonthlyInstallationsMetrics(@ParameterObject InstallationFilterParamsMonthly installationFilterParams){
        System.out.println(installationFilterParams);
        System.out.println(LocalDateTime.now());
        return installationService.getMonthlyInstallationMetrics(installationFilterParams);
    }


}
