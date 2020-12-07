package ar.edu.unq.desapp.grupoK.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.UserAdministrator;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto.DTOProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.CantFinishProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.FactorInvalid;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDateEndForProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidMinPercent;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.UserAdministratorService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.ErrorLoginUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.LocationAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Project> createProject(@Valid @RequestBody DTOProject dtoProject) throws InvalidMinPercent, FactorInvalid, InvalidDateEndForProject, LocationAlreadyExists {
        Project newProject = userAdministratorService.createProject(dtoProject);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/update/projects/{id}")
    public ResponseEntity<String> updateProject(
            @RequestBody DTOProject project,
            @PathVariable("id") Integer id) throws Exception {

        userAdministratorService.updateProject(id, project);

        return ResponseEntity.status(HttpStatus.OK).body("Project updated successfully");
    }

    @CrossOrigin
    @PutMapping("/update/locations/{id}")
    public ResponseEntity<String> updateLocation(
            @RequestBody Location location,
            @PathVariable("id") Integer id) throws Exception {

        userAdministratorService.updateLocation(id, location);
        return ResponseEntity.status(HttpStatus.OK).body("Location updated successfully");
    }

    @CrossOrigin
    @PutMapping("/finish_project")
    public ResponseEntity<?> finishCollection(@Valid @RequestBody DTOProject dtoProject) throws CantFinishProject {
        Project projectClosed = userAdministratorService.finishProject(dtoProject);
        return new ResponseEntity<>(projectClosed, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/top_10_donations")
    public ResponseEntity<List<String>> top10Donations() {
        List<String> donations =  userAdministratorService.top10Donations();
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/top10_locations")
    public ResponseEntity<List<String>> top10Location() {
        List<String> locations = userAdministratorService.topThe10LeastChosenLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}
