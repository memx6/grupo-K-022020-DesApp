package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.util.ArrayList;

public class User {
    String id;
    String name;
    String pass;
    String nick;
    Integer points;
    String email;
    Integer donatedMoney = 0;
    ArrayList<Donation> donatedProyects;

    User(String name,
         String pass,
         String nick,
         Integer points,
         String email){

            this.name = name;
            this.pass = pass;
            this.nick = nick;
            this.points = points;
            this.email = email;
    }

    public void donate (Proyect proyect, Integer money, String description){
        this.extraPoint();
        Donation newDonate = new Donation(proyect,this,description,money);
        proyect.receiveDonation(newDonate);
        this.donatedMoney += money;
    }

    private void extraPoint(){
        //validar por mismo mes.
        if(this.donatedProyects.size() > 1){
            this.points+=500;
        }
    }
    public void giveMePoints(Integer points) {
        this.points += points;
    }

}
