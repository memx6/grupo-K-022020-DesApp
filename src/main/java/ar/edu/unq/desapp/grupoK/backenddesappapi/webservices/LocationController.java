package ar.edu.unq.desapp.grupoK.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @CrossOrigin
    @GetMapping("/all_locations")
    public List<Location> allLocations() {
        return locationService.findAll();
    }
}
