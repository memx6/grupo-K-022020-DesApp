package ar.edu.unq.desapp.grupoK.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Donation;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto.DTODonation;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto.DTOUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDonatedMoney;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.UserService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.ErrorExistingUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.ErrorLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/users")
    public List<User> allUsers() {
        return userService.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userService.userById(id);
    }

    @CrossOrigin
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody User user) throws ErrorExistingUser {
        User newUser = userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    
    @CrossOrigin
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@Valid @RequestBody DTOUser dtoUser) throws ErrorLoginUser {
        try {
            User userLogin = userService.login(dtoUser);
            return new ResponseEntity<>(userLogin, HttpStatus.ACCEPTED);
        } catch (ErrorLoginUser e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @CrossOrigin
    @PutMapping("/update_user")
    public ResponseEntity<String> update_user(@Valid @RequestBody User user) {
        userService.update(user);
        return ResponseEntity.status(HttpStatus.OK).body("Your personal data has been updated successfully");

    }

    @CrossOrigin
    @PostMapping(value = "/donate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Donation> donate(@Valid @RequestBody DTODonation dtoDonation) throws InvalidDonatedMoney {
        Donation donation = userService.donate(dtoDonation);
        return new ResponseEntity<>(donation, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @GetMapping("/profile/{id}")
    public User userProfile(@PathVariable(value = "id") Integer id) {
        return userService.userProfile(id);
    }

    @CrossOrigin
    @GetMapping("/pro/{id}")
    public List<User> usersDonates(@PathVariable(value = "id") Integer id) {
        return userService.findByDonationsForProject(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
