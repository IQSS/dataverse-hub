package edu.harvard.iq.dataverse_hub.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;
import edu.harvard.iq.dataverse_hub.service.InstallationService;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/installation")
public class InstallationController {

    @Autowired
    private InstallationService installationService;

    @GetMapping
    public List<Installation> getInstallationsAPIController(){
        return installationService.findAll();
    }

    @PutMapping
    @Secured("DVH_ADMIN")
    public Installation createInstallation(@RequestBody Installation installation){
        return installationService.save(installation);
    }

    @GetMapping("status")
    public List<InstallationVersionInfo> geInstallationsStatus(){
        return installationService.getInstallationInfo();
    }


}
