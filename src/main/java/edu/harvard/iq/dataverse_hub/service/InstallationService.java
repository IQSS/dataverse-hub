package edu.harvard.iq.dataverse_hub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.repository.InstallationRepo;

import java.util.List;
import java.util.Optional;

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

    public void deleteById(String id) {
        installationRepo.deleteById(id);
    }

    public Optional<Installation> findById(String id) {
        return installationRepo.findById(id);
    }   

    public List<Installation> findAll() {
        return installationRepo.findAll();
    }

}
