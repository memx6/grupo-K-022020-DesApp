package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.LocationRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.LocationService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.LocationBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    @Test
    public void testLocationServiceFindAll() {
        MockitoAnnotations.initMocks(this);
        List<Location> locations = new ArrayList<>();
        locations.add(LocationBuilder.locationwithName("Avellaneda").build());
        locations.add(LocationBuilder.locationwithName("Bernal").build());
        when(locationRepository.findAll()).thenReturn(locations);
        List<Location> locationsRecovered = locationService.findAll();
        assertEquals(locations, locationsRecovered);
        assertEquals(2, locationsRecovered.size());
    }

    @Test
    public void testLocationServiceFindById() {
        MockitoAnnotations.initMocks(this);
        Location location = LocationBuilder.locationwithName("Avellaneda").build();
        when(locationRepository.findById(2)).thenReturn(Optional.of(location));
        assertEquals(location, locationService.findById(2));
    }
}