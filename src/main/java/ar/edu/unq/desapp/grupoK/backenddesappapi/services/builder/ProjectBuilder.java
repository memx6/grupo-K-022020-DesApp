package ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Donation;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectBuilder {
    private String proyectName;
    private Location location;
    private LocalDate dateStart = LocalDate.now();
    private LocalDate dateEnd ;
    private Integer minimumClosingPercentage = 100;
    private Integer factor = 1000;
    private Integer moneyNeededForProject = 0;
    private Integer moneyReceiveForProject = 0;
    private List<Donation> donations = new ArrayList<>();
    public boolean visibility = true;

    public static ProjectBuilder projectwithName(String name) {
        ProjectBuilder builder = new ProjectBuilder();
        builder.proyectName = name;
        return builder;
    }

    public Project build() {
        return new Project(proyectName, location, dateEnd, minimumClosingPercentage, factor);
    }

    public ProjectBuilder withMinPercentage(Integer minimumClosingPercentage) {
        this.minimumClosingPercentage = minimumClosingPercentage;
        return this;
    }

    public ProjectBuilder withDonations(ArrayList<Donation> donations) {
        this.donations = donations;
        return this;
    }

    public ProjectBuilder withVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }

    public ProjectBuilder withInitialDate(LocalDate dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public ProjectBuilder withDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public ProjectBuilder withLocation(Location location) {
        this.location = location;
        return this;
    }

    public ProjectBuilder withFactor(Integer factor) {
        this.factor = factor;
        return this;
    }
}
