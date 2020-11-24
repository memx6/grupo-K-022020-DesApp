package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.FactorInvalid;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDateEndForProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDonatedMoney;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidMinPercent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Column(unique = true)
    private String projectName;

    @OneToOne(cascade=CascadeType.ALL)
    private Location location;

    private LocalDate dateStart;
    @Future(message = "The Project end date must be after the start date")
    private LocalDate dateEnd ;
    @NotNull(message = "Minimum Closing Percentage is mandatory")
    private Integer minimumClosingPercentage = 100;
    private Integer factor = 1000;
    private Integer moneyNeededForProject = 0;
    private Integer moneyReceiveForProject = 0;

    //@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "project")
    @OneToMany(fetch=FetchType.LAZY, mappedBy = "project")
    @JsonIgnore
    private List<Donation> donations;

    public Boolean visibility = true;



    public Project() {}

    public Project(String name, Location location, LocalDate dateEnd, Integer percentageMinimum, Integer factor){
            this.projectName = name;
            this.donations = new ArrayList<>();
            this.location = location;
            this.dateStart = LocalDate.now();
            this.dateEnd = dateEnd;
            this.minimumClosingPercentage = (percentageMinimum >= 50 && percentageMinimum <= 100) ? percentageMinimum : 50;
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
        return this.projectName;
    }

    public Integer getMoneyReceiveForProject() { return this.moneyReceiveForProject;}

    public boolean getVisibility(){
        return this.visibility;
    }

    public void setVisibility(boolean bool){
        this.visibility = bool;
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
        this.addMoney(donation.getMoneyDonate());
        this.donations.add(donation);
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

    public List<Donation> getDonations(){
        return this.donations;
    }

    public void downProject(){
        location.setConnectivityState(true);
        this.visibility = false;
    }

    public boolean activeProject(){
        return this.visibility;
    }

    public Integer getPercentageCompleted(){
        return ((this.moneyReceiveForProject() / this.moneyNeededForProject())* 100);
    }
}
