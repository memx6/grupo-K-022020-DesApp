package ar.edu.unq.desapp.grupoK.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Donation;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration

@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @CrossOrigin
    @GetMapping("/donations")
    public List<Donation> allDonations() {
        return donationService.findAll();
    }

    @CrossOrigin
    @GetMapping("/donations/{id}")
    public Donation getDonation(@PathVariable("id") Integer id) {
        return donationService.findByID(id);
    }
}
