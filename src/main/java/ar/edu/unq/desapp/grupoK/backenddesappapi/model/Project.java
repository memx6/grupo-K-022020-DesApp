package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.FactorInvalid;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDateEndForProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDonatedMoney;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidMinPercent;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String proyectName;

    @OneToOne(cascade=CascadeType.ALL)
    private Location location;

    private LocalDate dateStart;
    private LocalDate dateEnd ;
    private Integer minimumClosingPercentage = 100;
    private Integer factor = 1000; // Cambiar factor por Double para poder poner porcentajes parciales
    private Integer moneyNeededForProject = 0;
    private Integer moneyReceiveForProject = 0;

    @OneToMany(mappedBy = "project")
    private List<Donation> donations;

    public boolean visibility = true;

    public Project() {}

    public Project(String name, Location location, LocalDate dateEnd, Integer percentageMinimum, Integer factor){
            this.proyectName = name;
            this.donations = new ArrayList<>();
            this.location = location;
            this.dateStart = LocalDate.now();
            this.dateEnd = dateEnd;
            this.minimumClosingPercentage = (percentageMinimum >= 50 && percentageMinimum <= 100) ? percentageMinimum : 50;;
            this.factor = factor;
            this.moneyNeededForProject= moneyNeededForProject();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName(){
        return this.proyectName;
    }

    public void setFactor (Integer newFactor) throws FactorInvalid {
        if (factor <= 0 || factor > 10000) {
            throw new FactorInvalid();
        }
        factor = newFactor;
    }

    public void setMinimumClosingPercentage(Integer minimumClosingPercentage) throws InvalidMinPercent {
        if (minimumClosingPercentage < 50 || minimumClosingPercentage > 100) {
            throw new InvalidMinPercent();
        }else {
            this.minimumClosingPercentage = minimumClosingPercentage;
        }
    }

    public Integer getMinimumClosingPercentage() {
        return minimumClosingPercentage;
    }

    public Location getLocation (){
        return this.location;
    }

    public LocalDate getDateEnd (){
        return this.dateEnd;
    }

    public Integer getFactor (){ return this.factor; }

    public void setDateEnd (LocalDate endDate) throws InvalidDateEndForProject {
        if (endDate.isBefore(this.dateStart)) {
            throw new InvalidDateEndForProject();
        }
        this.dateEnd = endDate;
    }

    public Integer moneyNeededForProject (){
        return moneyNeededForProject = location.getPopulation() * factor;
    }

    public void receiveDonation(Donation donation) throws InvalidDonatedMoney {
        this.donations.add(donation);
        this.addMoney(donation.getMoneyDonate());
    }

    public void addMoney(Integer money) throws InvalidDonatedMoney {
        if (money < 0) {
            throw new InvalidDonatedMoney();
        }
        this.moneyReceiveForProject += money;
    }

    public Integer moneyReceiveForProject (){
        return moneyReceiveForProject;
    }

    public List<Donation> allDonations(){
        return this.donations;
    }

    public void downProject(){
        this.visibility = false;
    }

    public boolean activeProject(){
        return this.visibility;
    }
}
