package edu.harvard.iq.dataverse_hub.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import edu.harvard.iq.dataverse_hub.service.InstallationService;

@RestController("/api/installation")
public class InstallationController {

    @Autowired
    private InstallationService installationService;

    @GetMapping("/")
    String getInstallationsAPIController(){
        return "Hello, World!";
    }

}
