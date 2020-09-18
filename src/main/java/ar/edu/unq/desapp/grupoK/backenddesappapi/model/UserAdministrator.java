package ar.edu.unq.desapp.grupoK.backenddesappapi.model;
import java.util.*;

public class UserAdministrator extends User {
    ArrayList<Proyect> proyects ;
    ArrayList<User> users;

    UserAdministrator(String name,
                      String pass,
                      String nick,
                      Integer points,
                      String email,
                      ArrayList<User> users,
                      ArrayList<Proyect> proyects) {
        super(name, pass, nick, points, email);
        this.users = users;
        this.proyects = proyects;
    }

    //Up and down.
}
