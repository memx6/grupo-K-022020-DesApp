package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

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

    @OneToOne
    private Location location;

    private LocalDate dateStart;
    private LocalDate dateEnd ;
    private Integer minimumClosingPercentage = 100;
    private Integer factor = 1000;
    private Integer moneyNeededForProject;
    private Integer moneyReceiveForProject = 0;

    @OneToMany(mappedBy = "project")
    private List<Donation> donations;

    public boolean visibility = true;

    public Project() {super();}

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

    public String getName(){
        return this.proyectName;
    }

    public void setFactor (Integer newFactor){
        factor = newFactor;
    }

    public void setMinimumClosingPercentage(Integer minimumClosingPercentage) {
        if (minimumClosingPercentage < 50 || minimumClosingPercentage > 100) {
            throw new IllegalArgumentException("The percentage is not within the allowed range, choose a range between 50 and 100");
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
