package ar.edu.unq.desapp.grupoK.backenddesappapi.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAdministratorTest {

    UserAdministrator userAdm;
    User user1;
    User user2;
    User user3;
    Project project1;
    Project project2;
    Project project3;
    Location location1;
    Location location2;
    Location location3;
    ArrayList<Donation> d1 = new ArrayList<Donation>();
    ArrayList<Donation> d2 = new ArrayList<Donation>();
    ArrayList<Donation> d3 = new ArrayList<Donation>();
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Project> proyects= new ArrayList<Project>();

    @BeforeEach
    void setUp() {
        location1 = new Location(" Wilde", "Buenos Aires", 10, false);
        location2 = new Location("Bernal", "Buenos Aires", 10, false);
        location3 = new Location(" Quilmes", "Mendoza", 10, false);
        project1 = new Project("Conectar igualdad", d1, location1, "enero", "agosto", 100, 0);
        project2 = new Project("Conectar igualdad", d2, location2, "enero", "agosto", 100, 1000);
        project3 = new Project("Conectar igualdad", d3, location3, "enero", "agosto", 100, 1000);
        user1 =  new User("Federico","pepito","Icardi","fedeericosanchez18@gmail.com");
        user2 =  new User("Mauro","pepita","Maxi Lopez","mauromarino@gmail.com");
        user3 =  new User("Joni","pepon","Jona","jonmaia@gmail.com");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        proyects.add(project1);
        proyects.add(project2);
        userAdm = new UserAdministrator("Super fede","shrek","Gobstone","fedex@gmail.com",users,proyects);
    }

    @Test
    void closeProject(){
        userAdm.closeProyect(project1);
        assertTrue(!project1.visibility);
    }



}
