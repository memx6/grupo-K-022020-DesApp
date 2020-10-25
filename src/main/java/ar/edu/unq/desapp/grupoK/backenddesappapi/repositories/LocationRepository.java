package ar.edu.unq.desapp.grupoK.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value= "SELECT TOP 10 (PROJECT.LOCATION) , PROJECT.NAME , COUNT(DONATION.ID) AS CANTIDAD FROM project INNER JOIN donation ON project.id== donation.project ORDER BY  CANTIDAD ASC",
            nativeQuery = true)
    List<Location> findTop10ByLocations();
}

