package ar.edu.unq.desapp.grupoK.backenddesappapi.services;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Donation;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.ProjectRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DonationService donationService;

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

    public Boolean existProjectWithLocation(Location location) {
        return this.projectRepository.existsProjectByLocation(location);
    }

    public List<Project> projectNearingCompletion(){
        List<Project> projectsOpen = this.openProjects();
        List<Project> projectsNearing = projectRepository.findByDateEndBetween(LocalDate.now());
        projectsOpen.removeAll(projectsNearing);
        projectsNearing.addAll(projectsOpen);
        return projectsNearing;
    }

    public List<String> top10Donations() {
        List<Donation> donations = donationService.findAll()
                .stream()
                .filter(donation -> donation.getDonationMonth().equals(LocalDate.now()))
                .collect(Collectors.toList());

        List<Donation> donationsTop10 = donations.stream()
                .sorted(comparing(Donation::getMoneyDonate).reversed())
                .limit(10)
                .collect(Collectors.toList());

        return donationsTop10.stream()
                .map(donation -> donation.getProject().getName())
                .collect(Collectors.toList());
    }

    public List<String> topThe10LeastChosenLocations() {
        List<Project> projects = openProjects();
        List<Donation> donations = new ArrayList<>();
        lastDonationsInAllProjects(projects, donations);

        tenDonations(donations);

        return tenLastNameOfLocations(donations);
    }

    private List<String> tenLastNameOfLocations(List<Donation> donations) {
        List<Location> lastLocations = donations.stream()
                .map(donation -> donation.getProject().getLocation())
                .collect(Collectors.toList());

        return lastLocations.stream()
                .map(location -> location.getProvince() + " - " + location.getName())
                .collect(Collectors.toList());
    }

    private void tenDonations(List<Donation> donations) {
        donations.stream()
                .sorted(comparing(Donation::getDonationMonth))
                .limit(10)
                .collect(Collectors.toList());
    }

    private void lastDonationsInAllProjects(List<Project> projects, List<Donation> donations) {
        projects.stream().forEach(project -> {
            if (project.getDonations().size() > 0) {
                donations.add(lastDonationInProject(project));
            }
        });
    }

    private Donation lastDonationInProject(Project project) {
        return project.getDonations()
                .stream()
                .sorted(comparing(Donation::getDonationMonth).reversed())
                .collect(Collectors.toList())
                .get(0);
    }
}
