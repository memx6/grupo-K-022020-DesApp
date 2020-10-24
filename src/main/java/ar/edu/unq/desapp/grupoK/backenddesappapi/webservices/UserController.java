package ar.edu.unq.desapp.grupoK.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto.DTOUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.UserService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.ErrorExistingUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.ErrorLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> allUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userService.userById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@Valid @RequestBody User user) throws ErrorExistingUser {
        User newUser = userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody DTOUser dtoUser) throws ErrorLoginUser {
        User userLogin = userService.login(dtoUser);
        return new ResponseEntity<>(userLogin, HttpStatus.ACCEPTED);
    }

    @GetMapping("/profile/{id}")
    public User userProfile(@PathVariable(value = "id") Integer id) {
        return userService.userProfile(id);
    }

    @GetMapping("/pro/{id}")
    public List<User> usersDonates(@PathVariable(value = "id") Integer id) {
        return userService.findByDonationsForProjectFinished(id);
    }
}
