package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.util.ArrayList;
import java.util.Calendar;

public class User {
    String name;
    String pass;
    String nick;
    Integer points;
    String email;
    Integer donatedMoney;
    Boolean flagDonate = false;
    int month;
    ArrayList<Proyect> donatedProyects;

    User(String name,
         String pass,
         String nick,
         Integer points,
         String email,
         Integer donatedMoney){

            this.name = name;
            this.pass = pass;
            this.nick = nick;
            this.points = points;
            this.email = email;
            this.donatedMoney = donatedMoney;
            this.month = Calendar.MONTH;

    }

    private void donate (Proyect proyect, Integer money){
        this.donatedProyects.add(proyect);
        proyect.receiveDonation(money, this);
        this.donatedMoney += money;
        validateFlagDonate();
    }

    public void giveMePoints(Integer points) {
        this.points += points;
    }

    private void validateFlagDonate (){
        if( Calendar.MONTH != this.month){
            this.flagDonate = false;
        }else {
            this.flagDonate = true;
        }
    }
}
