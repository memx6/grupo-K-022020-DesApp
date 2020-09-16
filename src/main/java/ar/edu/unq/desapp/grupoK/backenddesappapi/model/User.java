package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.util.ArrayList;
import java.util.Calendar;

public class User {
    String id;
    String name;
    String pass;
    String nick;
    Integer points;
    String email;
    Integer donatedMoney;
    ArrayList<Donate> donatedProyects;

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
            

    }

    private void donate (Proyect proyect, Integer money,String desciption){
        this.extraPoint();
        Donate newDonate = new Donate(proyect,this,desciption,money)
        proyect.receiveDonation(newDonate);
        this.donatedMoney += money;
        
    }

    private extraPoint(){
        //validar por mismo mes.
        if(this.donatedProyects.size() > 1){
            this.points+=500;
        }
    }
    public void giveMePoints(Integer points) {
        this.points += points;
    }

}
