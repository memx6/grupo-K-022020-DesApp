package ar.edu.unq.desapp.grupoK.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

    Optional<Location> findById(Integer id);

    List<Location> findAll();

    Location findByName(String name);

    //List<Location> findTop10ByLocations();
}

