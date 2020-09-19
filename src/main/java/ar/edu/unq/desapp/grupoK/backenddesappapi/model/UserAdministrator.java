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

    //Up and down.
}
