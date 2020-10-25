package ar.edu.unq.desapp.grupoK.backenddesappapi.services;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.ProjectRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private LocationService locationService;

    @Transactional
    public Project save(Project model) {
        return this.projectRepository.save(model);
    }

    public Project findById(Integer id) {
        return this.projectRepository.findById(id).get();
    }

    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    public List<Project> openProjects() {
        return projectRepository.findByVisibilityTrue();
    }

    public List<Project> projectNearingCompletion(){
        List<Project> projectsOpen = this.openProjects();
        List<Project> projectsNearing = projectRepository.findByDateEndBetween(LocalDate.now());
        projectsOpen.removeAll(projectsNearing);
        projectsNearing.addAll(projectsOpen);
        return projectsNearing;
    }

    public List<Project> top10Donations() {
        return projectRepository.findTop10ByDonations();
    }
}
