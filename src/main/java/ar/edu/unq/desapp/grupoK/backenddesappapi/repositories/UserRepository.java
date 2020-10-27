package ar.edu.unq.desapp.grupoK.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findById(Integer id);

    List<User> findAll();

    User findByName(String name);

    User findByEmail(String email);

    @Query(value = "SELECT DISTINCT (user_id) from donation " +
            "INNER JOIN project ON donation.project_id = ?1",
            nativeQuery = true)
    List<User> findByDonationsForProject(Integer idProject);

}
