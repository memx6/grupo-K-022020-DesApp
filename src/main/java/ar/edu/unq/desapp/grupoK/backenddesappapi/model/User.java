package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.util.ArrayList;

public class User {
    String id;
    String name;
    String pass;
    String nick;
    Integer points = 0;
    String email;
    Integer donatedMoney = 0;
    ArrayList<Donation> donatedProyects = new ArrayList<Donation>();

    User(String name,
         String pass,
         String nick,
         String email){

            this.name = name;
            this.pass = pass;
            this.nick = nick;
            this.email = email;
    }

    public void donate (Project project, Integer money, String description){
        Donation donation = new Donation(project,this,description,money);
        donation.executeDonation();
        this.donatedMoney += money;
        this.donatedProyects.add(donation);
    }

    public Integer myPoints(){
        return this.points;
    }

    public void giveMePoints(Integer points) {
        this.points += points;
    }

    public ArrayList<Donation> getDonatedProyects () {
        return this.donatedProyects;
    }

    public String getName(){
        return this.name;
    }

}
