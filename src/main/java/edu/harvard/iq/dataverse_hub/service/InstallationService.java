package edu.harvard.iq.dataverse_hub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.repository.InstallationRepo;

@Service
public class InstallationService {

    @Autowired
    private InstallationRepo installationRepo;

    public Installation findByName(String name) {
        return installationRepo.findByName(name);
    }

    public Installation save(Installation installation) {
        return installationRepo.save(installation);
    }

    public void delete(Installation installation) {
        installationRepo.delete(installation);
    }

    public void deleteById(Long id) {
        installationRepo.deleteById(id);
    }

    public Optional<Installation> findById(Long id) {
        return installationRepo.findById(id);
    }   

    public Iterable<Installation> findAll() {
        return installationRepo.findAll();
    }

}
