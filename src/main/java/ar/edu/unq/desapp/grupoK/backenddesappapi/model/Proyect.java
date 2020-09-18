package ar.edu.unq.desapp.grupoK.backenddesappapi.model;
import java.util.*;

public class Proyect {
    private String id;
    private String proyectName;
    private Location location;
    private String dateStart;
    private String dateEnd ;
    private Integer minimumClosingPercentage = 100;
    private Integer factor = 1000;
    private Integer moneyNeededForProject = 0;
    private Integer moneyReceiveForProject = 0;
    private ArrayList<Donation> donations;

    Proyect(String name, ArrayList<Donation> donor, Location location, String dateStart, String dateEnd, Integer percentageMinimum, Integer factor ){
            this.proyectName = name;
            this.donations = donor;
            this.location = location;
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
            this.minimumClosingPercentage = percentageMinimum;
            this.factor = factor;
    }

    private void setFactor (Integer newFactor){
        factor = newFactor;
    }

    public Integer moneyNeededForProject (){
        return moneyNeededForProject = location.population() * factor;
    }

    public void receiveDonation(Donation donation) {
        this.donations.add(donation);
        this.addMoney(donation.moneyDonate());
    }

    public void addMoney(Integer money){
        this.moneyReceiveForProject += money;
    }

    public Integer moneyReceiveForProject (){
        return moneyReceiveForProject;
    }

    public ArrayList<Donation> allDonations(){
        return donations;
    }


}
