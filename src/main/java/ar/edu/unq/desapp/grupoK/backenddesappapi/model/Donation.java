package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.time.LocalDate;

public class Donation {
    Proyect proyect;
    User user;
    String description;
    Integer money;
    LocalDate dateTrx;

    Donation(Proyect proyect, User user, String description, Integer money) {
        this.proyect = proyect;
        this.user = user;
        this.description = description;
        this.money = money;
        this.dateTrx = LocalDate.now();
    }

    public User userDonator() {
        return this.user;
    }

    public Integer moneyDonate() {
        return this.money;
    }
/*
    Integer point = this.calculatedPoint(donation.moneyDonate());
    User user = donation.userDonator();

    sendPointDonator(point, user);


    public Integer calculatedPoint(Integer money){
        Integer points = money;
        if ( money < 1000) {
            points = 200;
        }
        if ( location.population < 2000){
            points = points * 2;
        }
        return points;
    }
    public void sendPointDonator(Integer point, User user) {
        this.user.giveMePoints(point);
    }*/
}
