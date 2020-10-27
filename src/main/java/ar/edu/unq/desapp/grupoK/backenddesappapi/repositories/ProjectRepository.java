package ar.edu.unq.desapp.grupoK.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findById(Integer id);

    List<Project> findAll();

    List<Project> findByVisibilityTrue();

    @Query(value= "SELECT * FROM project p where DAY(p.date_end) >= DAY(?1)"
            + "AND MONTH(p.date_end) = MONTH(?1)"
            + "AND YEAR(p.date_end) = YEAR(?1)",
            nativeQuery = true)
    List<Project> findByDateEndBetween(LocalDate dateStart);

    @Query(value= "SELECT TOP 10 (PROJECT.ID) , PROJECT.NAME , COUNT(DONATION.ID) AS CANTIDAD FROM project " +
            "INNER JOIN donation ON project.id= donation.project ORDER BY  CANTIDAD DESC",
            nativeQuery = true)
    List<Project> findTop10ByDonations();

}
