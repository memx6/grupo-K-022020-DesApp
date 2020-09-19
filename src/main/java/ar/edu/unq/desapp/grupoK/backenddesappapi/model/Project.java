package ar.edu.unq.desapp.grupoK.backenddesappapi.model;
import java.util.*;

public class Project {
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

    Project(String name, ArrayList<Donation> donor, Location location, String dateStart, String dateEnd, Integer percentageMinimum, Integer factor ){
            this.proyectName = name;
            this.donations = donor;
            this.location = location;
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
            this.minimumClosingPercentage = percentageMinimum;
            this.factor = factor;
    }

    public void setFactor (Integer newFactor){
        factor = newFactor;
    }

    public void setMinimumClosingPercentage(Integer minimumClosingPercentage) {
        this.minimumClosingPercentage = minimumClosingPercentage;
    }

    public Integer getMinimumClosingPercentage() {
        return minimumClosingPercentage;
    }

    public Integer getFactor (){ return this.factor; }


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
        return this.donations;
    }

    public Location getLocation (){
        return this.location;
    }



}
