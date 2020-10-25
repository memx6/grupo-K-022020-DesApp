package ar.edu.unq.desapp.grupoK.backenddesappapi.services;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Transactional
    public Location save(Location model) {
        return this.locationRepository.save(model);
    }

    public Location findById(Integer id) {
        return this.locationRepository.findById(id).get();
    }

    public Location findByName(String name) {
        return this.locationRepository.findByName(name);
    }

    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }

    public List<Location> topThe10LeastChosenLocations() {
        return locationRepository.findTop10ByLocations();
    }
}
