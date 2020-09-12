package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

public class User {
    String name;
    String pass;
    String nick;
    Integer points;
    String email;
    Integer donatedMoney;

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

}
