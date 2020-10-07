package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDonatedMoney;
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
    void gettersAndSettersIsTested(){
        user.setName("mauro");
        user.setNick("mem");
        user.setPass("123");
        user.setEmail("mem@gmail.com");
        user.setDonatedMoney(1000);
        user.setMyPoints(10);
        assertEquals(user.getName(),"mauro");
        assertEquals(user.getNick(),"mem");
        assertEquals(user.getPass(),"123");
        assertEquals(user.getEmail(),"mem@gmail.com");
        assertEquals(user.getDonatedMoney(),1000);
        assertEquals(user.getMyPoints(),10);
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

