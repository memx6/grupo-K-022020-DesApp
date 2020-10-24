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

    User findByEmail(String email);

    @Query(value = "SELECT us.* FROM user us "
            +"INNER JOIN (SELECT dona.nickname FROM donation dona "
            +"INNER JOIN project p ON d.project_id = ?1) dp "
            +"ON don.nickname = dp.nickname",
            nativeQuery = true)
    List<User> findByDonationsForProjectFinished(Integer idProject);

}
