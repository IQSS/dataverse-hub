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
import io.swagger.v3.oas.annotations.Parameter;

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
        @Parameter(description = "Dataverse installation id", example = "dvh-0001-2018")
        @RequestParam(required = false) String dvHubId,
        @Parameter(description = "Name of the installation", example = "Harvard Dataverse")
        @RequestParam(required = false) String installationName,
        @Parameter(description = "Country of the installation", example = "USA")
        @RequestParam(required = false) String country,
        @Parameter(description = "Continent of the installation", example = "North America")
        @RequestParam(required = false) String continent,
        @Parameter(description = "Year of the installation launch", example = "2020")
        @RequestParam(required = false) Integer launchYear,
        @Parameter(description = "GDCC member status of the installation", example = "true")
        @RequestParam(required = false) Boolean gdccMember,
        @Parameter(description = "Maximum number of files in the installation", example = "100000")
        @RequestParam(required = false) Integer maxFiles,
        @Parameter(description = "Minimum number of files in the installation", example = "100")
        @RequestParam(required = false) Integer minFiles,
        @Parameter(description = "Maximum number of datasets in the installation", example = "10000")
        @RequestParam(required = false) Integer maxDatasets,
        @Parameter(description = "Minimum number of datasets in the installation", example = "100")
        @RequestParam(required = false) Integer minDatasets,
        @Parameter(description = "Maximum number of dataverses in the installation", example = "100")
        @RequestParam(required = false) Integer maxDataverses,
        @Parameter(description = "Minimum number of dataverses in the installation", example = "10")
        @RequestParam(required = false) Integer minDataverses,
        @Parameter(description = "Maximum number of harvested datasets in the installation", example = "1000")
        @RequestParam(required = false) Integer maxHarvested,
        @Parameter(description = "Minimum number of harvested datasets in the installation", example = "100")
        @RequestParam(required = false) Integer minHarvested,
        @Parameter(description = "Minimum number of local dataverses in the installation", example = "100")
        @RequestParam(required = false) Integer minLocalDataverses,
        @Parameter(description = "Maximum number of local dataverses in the installation", example = "1000")
        @RequestParam(required = false) Integer maxLocalDataverses){

        return installationService.getInstallationMetrics(dvHubId, installationName, country, continent, launchYear, gdccMember, maxFiles, minFiles, maxDatasets, minDatasets, maxDataverses, minDataverses, maxHarvested, minHarvested, maxLocalDataverses, minLocalDataverses);
    }

    @GetMapping("metrics/monthly")
    @InstallationControllerDocs.getMonthlyInstallationsMetrics
    public List<InstallationMetrics> getMonthlyInstallationsMetrics(
        @Parameter(description = "Dataverse installation id", example = "dvh-0001-2018")
        @RequestParam(required = false) String dvHubId,
        @Parameter(description = "Name of the installation", example = "Harvard Dataverse")
        @RequestParam(required = false) String installationName,
        @Parameter(description = "Country of the installation", example = "USA")
        @RequestParam(required = false) String country,
        @Parameter(description = "Continent of the installation", example = "North America")
        @RequestParam(required = false) String continent,
        @Parameter(description = "Year of the installation launch", example = "2020")
        @RequestParam(required = false) Integer launchYear,
        @Parameter(description = "GDCC member status of the installation", example = "true")
        @RequestParam(required = false) Boolean gdccMember,
        @Parameter(description = "Maximum number of files in the installation", example = "100000")
        @RequestParam(required = false) Integer maxFiles,
        @Parameter(description = "Minimum number of files in the installation", example = "100")
        @RequestParam(required = false) Integer minFiles,
        @Parameter(description = "Maximum number of datasets in the installation", example = "10000")
        @RequestParam(required = false) Integer maxDatasets,
        @Parameter(description = "Minimum number of datasets in the installation", example = "100")
        @RequestParam(required = false) Integer minDatasets,
        @Parameter(description = "Maximum number of dataverses in the installation", example = "100")
        @RequestParam(required = false) Integer maxDataverses,
        @Parameter(description = "Minimum number of dataverses in the installation", example = "10")
        @RequestParam(required = false) Integer minDataverses,
        @Parameter(description = "Maximum number of harvested datasets in the installation", example = "1000")
        @RequestParam(required = false) Integer maxHarvested,
        @Parameter(description = "Minimum number of harvested datasets in the installation", example = "100")
        @RequestParam(required = false) Integer minHarvested,
        @Parameter(description = "Minimum number of local dataverses in the installation", example = "100")
        @RequestParam(required = false) Integer minLocalDataverses,
        @Parameter(description = "Maximum number of local dataverses in the installation", example = "1000")
        @RequestParam(required = false) Integer maxLocalDataverses){
       
        return null;//installationService.getInstallationMetrics(country);
    }


}
