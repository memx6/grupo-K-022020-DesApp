package ar.edu.unq.desapp.grupoK.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.UserAdministrator;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto.DTOProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.FactorInvalid;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDateEndForProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidMinPercent;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.UserAdministratorService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.ErrorLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/admin")
public class UserAdministratorController {

    @Autowired
    private UserAdministratorService userAdministratorService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<UserAdministrator> login(@Valid @RequestBody UserAdministrator admin) throws ErrorLoginUser {
        UserAdministrator adminLogin = userAdministratorService.login(admin.getEmail(), admin.getPassword());
        return new ResponseEntity<>(adminLogin, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @PostMapping("/create/project/")
    @ExceptionHandler({ InvalidDateEndForProject.class, InvalidMinPercent.class, FactorInvalid.class })
    public ResponseEntity<Project> createProject(@Valid @RequestBody DTOProject dtoProject) throws InvalidMinPercent, FactorInvalid, InvalidDateEndForProject {
        Project newProject = userAdministratorService.createProject(dtoProject);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/finish_project")
    public ResponseEntity<Project> finishCollection(@Valid @RequestBody DTOProject dtoProject) {
        Project projectClosed = userAdministratorService.finishProject(dtoProject);
        return new ResponseEntity<>(projectClosed, HttpStatus.OK);
    }

    @CrossOrigin
    // Pongo get para ver los envios pero manejalo como te parezca
    @GetMapping("/top_10_donations")
    public void top10() {
        userAdministratorService.top10Donations();
    }

    @CrossOrigin
    @GetMapping("/top10_locations")
    public void top10Location() {
        userAdministratorService.topThe10LeastChosenLocations();
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
