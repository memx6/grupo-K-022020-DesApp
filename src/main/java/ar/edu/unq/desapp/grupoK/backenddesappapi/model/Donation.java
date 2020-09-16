package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

public class Donation {
        Proyect proyect;
        User user;
        String description;
        Integer money;
        Date dateTrx = new Date();

        Donation(proyect , user , description , money) {
                this.proyect = proyect;
                this.user = user;
                this.description = description;
                this.money = money;
        }

       public User getUser() {
           return this.user;
       } 
}
