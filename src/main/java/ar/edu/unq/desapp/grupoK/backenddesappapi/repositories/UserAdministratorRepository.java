package ar.edu.unq.desapp.grupoK.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.UserAdministrator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Configuration
@Repository
public interface UserAdministratorRepository extends CrudRepository<UserAdministrator, Integer> {

    Optional<UserAdministrator> findById(Integer id);

    UserAdministrator findByEmail(String email);

}
