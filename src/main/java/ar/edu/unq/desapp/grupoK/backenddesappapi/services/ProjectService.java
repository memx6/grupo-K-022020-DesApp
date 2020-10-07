package ar.edu.unq.desapp.grupoK.backenddesappapi.services;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.ProjectRepository;
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

    public Project findByID(Integer id) {
        return this.projectRepository.findById(id).get();
    }

    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }
}
