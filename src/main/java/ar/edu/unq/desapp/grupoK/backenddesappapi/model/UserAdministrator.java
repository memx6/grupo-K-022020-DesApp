package ar.edu.unq.desapp.grupoK.backenddesappapi.model;
import java.util.*;

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

    public void closeProyect(Project project) {
        if(project.projectCanBeClosed()){
            project.downProject();
            for (int i = 0 ; i < project.allDonations().size() ; i ++){
                this.sendMailForDonated(project , project.allDonations().get(i).user);
            }

        }

    }

    private void sendMailForDonated(Project project , User user){

        System.out.println("this proyect " + project.getName() + " is close , tanks " + user.getName() + " for your colaboration. ");
    }

    //Up and down.
}
