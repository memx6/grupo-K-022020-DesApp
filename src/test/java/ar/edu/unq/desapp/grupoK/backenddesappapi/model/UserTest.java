package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.FactorInvalid;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDateEndForProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDonatedMoney;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidMinPercent;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private Project project ;
    private User user;
    private Location location ;

    @BeforeEach
    void setUp () throws InvalidMinPercent, FactorInvalid, InvalidDateEndForProject {
        location = new Location("Avellaneda","Buenos Aires",10,false);
        project = new Project("Conectar Igualdad", location, LocalDate.parse("2020-12-11"),  100, 1000);
        user  = new User("Federico","pepito","Fefi","fedeericosanchez18@gmail.com");
    }

    @Test
    void gettersAndSettersIsTested(){
        user.setName("mauro");
        user.setNick("mem");
        user.setPassword("123");
        user.setEmail("mem@gmail.com");
        user.setDonatedMoney(1000);
        user.setMyPoints(10);
        user.setRol(Rol.ROLE_USER);
        assertEquals(user.getName(),"mauro");
        assertEquals(user.getNick(),"mem");
        assertEquals(user.getPassword(),"123");
        assertEquals(user.getEmail(),"mem@gmail.com");
        assertEquals(user.getDonatedMoney(),1000);
        assertEquals(user.getMyPoints(),10);
        assertEquals(user.getRol(),Rol.ROLE_USER);

    }

    @Test
    void donatedThenAddList() throws InvalidDonatedMoney {
        user.donate(project,100,"Suerte!");
        assertEquals(user.getDonatedProyects().size(),1);
    }

    @Test
    void addMoneyWithDonated() throws InvalidDonatedMoney {
        user.donate(project,100,"Suerte!");
        assertEquals(user.donatedMoney,100);
    }
}

