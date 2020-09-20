package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserAdministrator extends User {
    ArrayList<Project> projects;
    ArrayList<User> users;

    UserAdministrator(String name,
                      String pass,
                      String nick,
                      String email,
                      ArrayList<User> users,
                      ArrayList<Project> projects) {
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
            project.allDonations().forEach(donation -> this.sendMailForDonated(project, donation.userDonator()));
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
