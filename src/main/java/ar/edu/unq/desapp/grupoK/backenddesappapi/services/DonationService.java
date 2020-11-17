package ar.edu.unq.desapp.grupoK.backenddesappapi.services;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Donation;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Transactional
    public Donation save(Donation model) {
        return this.donationRepository.save(model);
    }

    public Donation findByID(Integer id) {
        return this.donationRepository.findById(id).get();
    }

    public List<Donation> findAll() {
        return this.donationRepository.findAll();
    }

    public List<Donation> findByProject(Integer idProject) {
        return this.donationRepository.findByProject(idProject);
    }

}
