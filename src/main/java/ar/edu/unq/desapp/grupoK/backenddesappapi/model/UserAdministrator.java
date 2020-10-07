package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserAdministrator extends User {

    @OneToMany
    protected List<Project> projects = new ArrayList<>();

    @OneToMany
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

    public Project createProject(String name, Location location, LocalDate dateEnd, Integer percentageMinimum, Integer factor) {
        return new Project(name,  location, dateEnd,percentageMinimum, factor);
    }

    public void closeProyect(Project project) {
        if(this.projectCanBeClosed(project)){
            project.downProject();
            project.allDonations().forEach(donation -> this.sendMailForDonated(project, donation.getUserDonator()));
        }
    }

    public boolean projectCanBeClosed(Project project){
        return  minimumPercentageCompleted(project); //&& project.getDateEnd() == LocalDate.now();
    }

    public boolean minimumPercentageCompleted(Project project) {
        return ((project.moneyReceiveForProject() / project.moneyNeededForProject())* 100) >= project.getMinimumClosingPercentage();
    }

    private void sendMailForDonated(Project project , User user){
        System.out.println("this proyect " + project.getName() + " is close , tanks " + user.getName() + " for your colaboration. ");
    }
}
