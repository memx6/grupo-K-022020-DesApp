package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.FactorInvalid;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDateEndForProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidMinPercent;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.ProjectRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.ProjectService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.LocationBuilder;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.ProjectBuilder;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Test
    public void testProjectServiceFindAll() throws InvalidDateEndForProject, FactorInvalid, InvalidMinPercent {
        MockitoAnnotations.initMocks(this);
        Location location = LocationBuilder.locationwithName("Avellaneda").withProvince("Buenos Aires")
                .withPopulation(1000)
                .withConnectivityState(true)
                .build();
        List<Project> projects = new ArrayList<>();
        projects.add(ProjectBuilder.projectwithName("Proyecto Avellaneda")
                .withFactor(1000)
                .withDateEnd(LocalDate.parse("2020-12-11"))
                .withLocation(location).build());
        projects.add(ProjectBuilder.projectwithName("Proyecto Quilmes")
                .withFactor(1000)
                .withDateEnd(LocalDate.parse("2020-12-11"))
                .withLocation(location).build());
        when(projectRepository.findAll()).thenReturn(projects);
        List<Project> projectsRecovered = projectService.findAll();
        assertEquals(projects, projectsRecovered);
        assertEquals(2, projectsRecovered.size());
    }

    @Test
    public void testProjectServiceFindById() throws InvalidDateEndForProject, FactorInvalid, InvalidMinPercent {
        MockitoAnnotations.initMocks(this);
        Location location = LocationBuilder.locationwithName("Avellaneda").withProvince("Buenos Aires")
                .withPopulation(1000)
                .withConnectivityState(true)
                .build();
        Project project = ProjectBuilder.projectwithName("Proyecto Avellaneda")
                .withFactor(1000)
                .withDateEnd(LocalDate.parse("2020-12-11"))
                .withLocation(location).build();
        when(projectRepository.findById(2)).thenReturn(Optional.of(project));
        assertEquals(project, projectService.findById(2));
    }
}