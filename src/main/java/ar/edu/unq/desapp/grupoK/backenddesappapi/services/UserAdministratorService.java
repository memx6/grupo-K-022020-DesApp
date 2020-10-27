package ar.edu.unq.desapp.grupoK.backenddesappapi.services;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.UserAdministrator;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto.DTOProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.CantFinishProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.FactorInvalid;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDateEndForProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidMinPercent;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.UserAdministratorRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.ErrorLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
public class UserAdministratorService {

    @Autowired
    private UserAdministratorRepository admRepository;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Transactional
    public UserAdministrator save(UserAdministrator model) {
        return this.admRepository.save(model);
    }

    @ExceptionHandler(ErrorLoginUser.class)
    public UserAdministrator login(String email, String password) throws ErrorLoginUser {
        UserAdministrator adminLogin = admRepository.findByEmail(email);
        if (!(adminLogin.getPassword().equals(password))) {
            throw new ErrorLoginUser();
        }
        return adminLogin;
    }

    @ExceptionHandler({ InvalidDateEndForProject.class, InvalidMinPercent.class, FactorInvalid.class })
    public Project createProject(DTOProject dtoProject) throws InvalidDateEndForProject, FactorInvalid, InvalidMinPercent {
        UserAdministrator admin = admRepository.findById(dtoProject.getIdUserAdmin()).get();
        Location newLocation = locationService.findByName(dtoProject.getLocationName());

        Project newProject = admin.createProject(dtoProject.getProjectName(),
                newLocation,
                dtoProject.getDateEnd(),
                dtoProject.getMinimumClosingPercentage(),
                dtoProject.getFactor());
        projectService.save(newProject);
        return newProject;
    }

    public Project finishProject(DTOProject dtoProject) throws CantFinishProject {
        UserAdministrator admin = admRepository.findById(dtoProject.getIdUserAdmin()).get();
        Project project = projectService.findById(dtoProject.getIdProject());
        admin.closeProject(project);
        Project projectClosed = projectService.save(project);
        return projectClosed;
    }

    public void top10Donations() {
        List<Project> projects = projectService.top10Donations();
        List<User> users = userService.findAll();
        //acá enviaria email de notificacion diaria
    }

    public List<Location> topThe10LeastChosenLocations() {
        List<Location> locations = locationService.topThe10LeastChosenLocations();
        List<User> users = userService.findAll();
        //acá enviaria email de notificacion diaria
        return locations;
    }
}