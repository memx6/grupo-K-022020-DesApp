package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.CantFinishProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.FactorInvalid;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDateEndForProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidMinPercent;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserAdministrator extends User {

    @OneToMany(fetch = FetchType.LAZY)
    protected List<Project> projects = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    protected List<User> users = new ArrayList<>();

    public UserAdministrator(){}

    public UserAdministrator(String name,
                      String pass,
                      String nick,
                      String email,
                      List<User> users,
                      List<Project> projects) {
        super(name, pass, nick, email);
        this.users = users;
        this.projects = projects;
    }

    public Project createProject(String name, Location location, LocalDate dateEnd, Integer percentageMinimum, Integer factor) throws InvalidMinPercent, FactorInvalid, InvalidDateEndForProject {
        return new Project(name, location, dateEnd, percentageMinimum, factor);
    }

    public void closeProject(Project project) throws CantFinishProject {
        if(this.projectCanBeClosed(project)){
            project.downProject();
            project.getDonations().forEach(donation -> this.sendMailForDonated(project, donation.getUserDonator()));
        }else{
            throw new CantFinishProject();
        }
    }

    public boolean projectCanBeClosed(Project project){
        return  minimumPercentageCompleted(project) && onDateToEnd(project);
    }

    private boolean onDateToEnd(Project project) {
        return project.getDateEnd().isAfter(LocalDate.now()) || project.getDateEnd().isEqual(LocalDate.now());
    }

    public boolean minimumPercentageCompleted(Project project) {
        return project.getPercentageCompleted() >= project.getMinimumClosingPercentage();
    }

    private void sendMailForDonated(Project project , User user){
        System.out.println("this proyect " + project.getName() + " is close , tanks " + user.getName() + " for your colaboration. ");
    }
}
