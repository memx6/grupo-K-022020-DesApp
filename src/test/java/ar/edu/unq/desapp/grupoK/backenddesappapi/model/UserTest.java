package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private Project project ;
    private User user;
    private Location location ;

    @BeforeEach
    void setUp (){
        location = new Location("Avellaneda","Buenos Aires",10,false);
        project = new Project("Conectar Igualdad", location, LocalDate.of(2020,12,11),  100, 1000);
        user  = new User("Federico","pepito","Fefi","fedeericosanchez18@gmail.com");
    }

    @Test
    void giveMePoint(){
        user.giveMePoints(10);
        assertEquals(user.myPoints(),10);
    }

    @Test
    void donatedThenAddList(){
        user.donate(project,100,"Suerte!");
        assertEquals(user.getDonatedProyects().size(),1);
    }

    @Test
    void addMoneyWithDonated(){
        user.donate(project,100,"Suerte!");
        assertEquals(user.donatedMoney,100);
    }
}

