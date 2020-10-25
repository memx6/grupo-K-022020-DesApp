package ar.edu.unq.desapp.grupoK.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @CrossOrigin
    @GetMapping("/projects")
    public List<Project> allProjects() {
        return projectService.findAll();
    }

    @CrossOrigin
    @GetMapping("/projects_open")
    public List<Project> projectOpen() {
        return projectService.openProjects();
    }

    @CrossOrigin
    @GetMapping("/project_nearing")
    public List<Project> projectNearingCompletion() {
        return projectService.projectNearingCompletion();
    }

    @CrossOrigin
    @GetMapping("/projects/{id}")
    public Project getProject(@PathVariable("id") Integer id) {
        return projectService.findById(id);
    }
}
