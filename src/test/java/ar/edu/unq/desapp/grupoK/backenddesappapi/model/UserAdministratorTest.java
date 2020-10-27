package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.*;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Project> proyects= new ArrayList<>();

    @BeforeEach
    void setUp() throws InvalidMinPercent, FactorInvalid, InvalidDateEndForProject {
        location1 = new Location(" Wilde", "Buenos Aires", 10, false);
        location2 = new Location("Bernal", "Buenos Aires", 10, false);
        location3 = new Location(" Quilmes", "Mendoza", 10, false);
        project1 = new Project("Conectar igualdad", location1, LocalDate.parse("2020-12-11"),  100, 1000);
        project2 = new Project("Conectar igualdad", location2, LocalDate.parse("2020-12-11"), 100, 1000);
        project3 = new Project("Conectar igualdad", location3, LocalDate.parse("2020-12-11"),  100, 1000);
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
    void whenAAdminCreateProjectShouldReturnAValidProject() throws InvalidDateEndForProject, FactorInvalid, InvalidMinPercent {
        Location location = mock(Location.class);

        Project newProject = Mockito.spy(userAdm.createProject("project1", location1, LocalDate.parse("2020-12-11"), 60, 1000));

        when(newProject.getName()).thenReturn("project1");
        when(newProject.getMinimumClosingPercentage()).thenReturn(60);
        when(newProject.getDateEnd()).thenReturn(LocalDate.parse("2020-12-11"));
        when(newProject.getLocation()).thenReturn(location);
        when(newProject.getFactor()).thenReturn(1000);
        when(newProject.moneyNeededForProject()).thenReturn(1000);

        assertEquals(newProject.getName(), "project1");
        assertEquals(newProject.getMinimumClosingPercentage(), 60);
        assertEquals(newProject.getDateEnd(), LocalDate.parse("2020-12-11"));
        assertEquals(newProject.getLocation(), location);
        assertEquals(newProject.getFactor(), 1000);
        when(newProject.moneyNeededForProject()).thenReturn(1000);
    }

    @Test
    void closeProject() throws InvalidDonatedMoney, CantFinishProject {
        user1.donate(project1,10000, "I donate the total to finish the project");
        userAdm.closeProject(project1);
        assertTrue(!project1.visibility);
    }
}
