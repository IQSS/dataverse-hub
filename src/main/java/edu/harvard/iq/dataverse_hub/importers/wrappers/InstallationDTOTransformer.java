package edu.harvard.iq.dataverse_hub.importers.wrappers;

import java.util.List;
import java.util.ArrayList;
import edu.harvard.iq.dataverse_hub.model.Installation;

public class InstallationDTOTransformer {

    public static Installation transform(InstallationWrapper installationWrapper) {
        Installation installation = new Installation();
        installation.setName(installationWrapper.getName());

        return installation;
    }

    public static List<Installation> transform(GitHubInstallationWrapper gitHubInstallationWrapper) {
        List<Installation> installations = new ArrayList<Installation>();
        for (InstallationWrapper installationWrapper : gitHubInstallationWrapper.getInstallations()) {
            installations.add(transform(installationWrapper));
        }
        
        return installations;
    }
    

}
