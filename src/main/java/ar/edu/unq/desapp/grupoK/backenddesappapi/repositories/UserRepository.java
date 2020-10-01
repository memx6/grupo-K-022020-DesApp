package ar.edu.unq.desapp.grupoK.backenddesappapi.repositories;

import java.util.List;
import java.util.Optional;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Configuration
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findById(Integer id);

    List<User> findAll();

}
