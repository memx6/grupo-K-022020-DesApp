package ar.edu.unq.desapp.grupoK.backenddesappapi.services;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto.DTOProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.CantFinishProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.FactorInvalid;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDateEndForProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidMinPercent;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.UserAdministratorRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.ErrorLoginUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.LocationAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private DonationService donationService;

    @Autowired
    private EmailService emailService;

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

    @ExceptionHandler({ InvalidDateEndForProject.class, InvalidMinPercent.class, FactorInvalid.class, LocationAlreadyExists.class })
    public Project createProject(DTOProject dtoProject) throws InvalidDateEndForProject, FactorInvalid, InvalidMinPercent, LocationAlreadyExists {
        UserAdministrator admin = admRepository.findById(dtoProject.getIdUserAdmin()).get();
        Location newLocation = locationService.findByName(dtoProject.getLocationName());
        if (projectService.existProjectWithLocation(newLocation)) {
            throw new LocationAlreadyExists();
        }
        Project newProject = admin.createProject(dtoProject.getProjectName(),
                    newLocation,
                    dtoProject.getDateEnd(),
                    dtoProject.getMinimumClosingPercentage(),
                    dtoProject.getFactor());
        projectService.save(newProject);
        return newProject;
    }

    @ExceptionHandler(CantFinishProject.class)
    @Transactional
    public Project finishProject(DTOProject dtoProject) throws CantFinishProject {
        UserAdministrator admin = admRepository.findById(dtoProject.getIdUserAdmin()).get();
        Project project = projectService.findById(dtoProject.getIdProject());
        admin.closeProject(project);
        Project projectClosed = projectService.save(project);
        List<User> users = findUsersByProject(projectClosed);
        //emailService.sendMailForDonated(users, project);
        return projectClosed;
    }

    private List<User> findUsersByProject(Project project) {
        return project.getDonations()
                .stream()
                .map(Donation::getUser)
                .collect(Collectors.toList());
    }

    public List<String> top10Donations() {
        List<String> projects = projectService.top10Donations();
        return projects;
    }

    public List<String> topThe10LeastChosenLocations() {
        List<String> locations = projectService.topThe10LeastChosenLocations();
        return locations;
    }
}
