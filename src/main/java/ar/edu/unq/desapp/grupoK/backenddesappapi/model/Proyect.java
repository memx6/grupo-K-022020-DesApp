package ar.edu.unq.desapp.grupoK.backenddesappapi.model;
import java.util.*;

public class Proyect {
    String proyectName;
    ArrayList<User> donor;
    Location location;
    String dateEnd ;
    String dateStart;
    Integer percentage = 100;
    Integer factor = 1000;
    Integer moneyNeededForProject = 0;
    Integer moneyReceiveForProject = 0;

    Proyect(String name , ArrayList<User> donor, Location location , String dateEnd ,String dateStart ,Integer percentage ,Integer factor ){
            this.proyectName = name;
            this.donor = donor;
            this.location = location;
            this.dateEnd = dateEnd;
            this.dateStart = dateStart;
            this.percentage = percentage;
            this.factor = factor;
    }

    private Integer moneyNeededForProject (){
        return moneyNeededForProject = location.population * factor;
    }

    private void setFactor (Integer newFactor){
        factor = newFactor;
    }

    public void receiveDonation(Integer money, User user) {
        moneyReceiveForProject += money;
        donor.add(user);
        sendPointDonator(money, user);
    }

    private void sendPointDonator(Integer money, User user) {
        Integer points = money;
        if ( money < 1000) {
            points = 200;
        }
        if ( location.population < 2000){
            points = points * 2;
        }
        user.giveMePoints(points);
    }
}
