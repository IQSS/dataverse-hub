package edu.harvard.iq.dataverse_hub.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.harvard.iq.dataverse_hub.controller.api.annotations.InstallationControllerDocs;
import edu.harvard.iq.dataverse_hub.controller.api.response.InstallationsByCountry;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationMetrics;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;
import edu.harvard.iq.dataverse_hub.service.InstallationService;

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
    public List<InstallationMetrics> getInstallationsMetrics(
        @RequestParam(required = false) String country,
        @RequestParam(required = false) String continent,
        @RequestParam(required = false) Integer launchYear,
        @RequestParam(required = false) Boolean gdccMember,
        @RequestParam(required = false) Integer maxFiles,
        @RequestParam(required = false) Integer minFiles,
        @RequestParam(required = false) Integer maxDatasets,
        @RequestParam(required = false) Integer minDatasets,
        @RequestParam(required = false) Integer maxDataverses,
        @RequestParam(required = false) Integer minDataverses,
        @RequestParam(required = false) Integer maxHarvested,
        @RequestParam(required = false) Integer minHarvested,
        @RequestParam(required = false) Integer minLocalDataverses,
        @RequestParam(required = false) Integer maxLocalDataverses)
        {
        return installationService.getInstallationMetrics(country, continent, launchYear, gdccMember, maxFiles, minFiles, maxDatasets, minDatasets, maxDataverses, minDataverses, maxHarvested, minHarvested, minLocalDataverses, maxLocalDataverses);
    }

    @GetMapping("metrics/monthly")
    //@InstallationControllerDocs.getMonthlyInstallationsMetrics
    public List<InstallationMetrics> getMonthlyInstallationsMetrics(String country){
        return null;//installationService.getInstallationMetrics(country);
    }


}
