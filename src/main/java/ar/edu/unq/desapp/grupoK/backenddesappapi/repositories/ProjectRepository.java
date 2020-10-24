package ar.edu.unq.desapp.grupoK.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findById(Integer id);

    List<Project> findAll();

    List<Project> findByVisibilityTrue();

    List<Project> findByDateEndBetween(LocalDate dateStart, LocalDate dateEnd);

    default List<Project> findByDateEndBetween(LocalDate givenDate) {
        return findByDateEndBetween(givenDate, givenDate);
    }

    //List<Project> findTop10ByDonations();

}
