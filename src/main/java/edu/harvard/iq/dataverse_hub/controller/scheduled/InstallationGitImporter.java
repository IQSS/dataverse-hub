package edu.harvard.iq.dataverse_hub.controller.scheduled;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.harvard.iq.dataverse_hub.importers.wrappers.GitHubInstallationWrapper;
import edu.harvard.iq.dataverse_hub.importers.wrappers.InstallationDTOTransformer;

import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.service.InstallationService;

import java.util.List;
import java.util.Date;

@Component
public class InstallationGitImporter {

    private static final String INSTALLATIONS_URL = "https://raw.githubusercontent.com/IQSS/dataverse-installations/refs/heads/main/data/data.json";

    @Autowired
    private InstallationService installationService;

    @Scheduled(fixedRate = 60000)
    public void importInstallations() {
        System.out.println("Importing installations...");
        
        
        RestTemplate restTemplate = new RestTemplate();
        String jsonImport = restTemplate.getForObject(INSTALLATIONS_URL, String.class); 

        try {
            ObjectMapper mapper = new ObjectMapper();
            GitHubInstallationWrapper installations = mapper.readValue(jsonImport, GitHubInstallationWrapper.class);

            List<Installation> dtos = InstallationDTOTransformer.transform(installations);
            for (Installation installation : dtos) {
                Installation existingInstallation = installationService.findByName(installation.getName());
                if (existingInstallation == null) {
                    installation.setDvHubId(new Date().toString() + installation.getName());
                    installationService.save(installation);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

}
