package ar.edu.unq.desapp.grupoK.backenddesappapi.model;
import java.util.*;

public class Proyect {
    String id;
    String proyectName;
    Location location;
    String dateEnd ;
    String dateStart;
    Integer percentage = 100;
    Integer factor = 1000;
    Integer moneyNeededForProject = 0;
    Integer moneyReceiveForProject = 0;
    ArrayList<Donation> donation;

    Proyect(String name , ArrayList<Donation> donor, Location location , String dateEnd ,String dateStart ,Integer percentage ,Integer factor ){
            this.proyectName = name;
            this.donation = donor;
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

    public void receiveDonation(Donation donation) {
        this.donation.add(donation);
        this.addMoney(money);
        Integer point = this.calculatedPoint(money);
        User user = donation.getUser();
        sendPointDonator(point,user);
    }

    public void addMoney(Integer money){
        this.moneyReceiveForProject += money;
    }

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
    private void sendPointDonator(Integer point, User user) {
         user.giveMePoints(point);
    }
    

}
