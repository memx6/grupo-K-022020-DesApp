package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDonatedMoney;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.joda.time.LocalDate;

@Entity
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String description;
    @NotNull(message = "Money is mandatory")
    @Min(10)
    private Integer money;
    private LocalDate dateTrx;

    public Donation() {}

    public Donation(Project project, User user, String description, Integer money) {
        this.project = project;
        this.user = user;
        this.description = description;
        this.money = money;
        this.dateTrx = LocalDate.now();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDonationMonth(){
        return this.dateTrx;
    }

    public User getUserDonator() {
        return this.user;
    }

    public void setMoneyDonate(Integer money) {
        this.money = money;
    }

    public Integer getMoneyDonate() {
        return this.money;
    }

    public User getUser() {
        return user;
    }

    public Project getProject() {
        return project;
    }

    public Integer getPoints() {
        return this.user.getMyPoints();
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){this.description = description;}

    public void executeDonation() throws InvalidDonatedMoney {
        this.calculatePointsObtained();
        this.project.receiveDonation(this);
    }

    public void calculatePointsObtained() {
        Integer moneyPoints = this.pointsMoneyMoreThousand();
        Integer populationPoints = this.pointsPopulation();
        this.pointsPerMonthCalender();
        this.getUserDonator().giveMePoints(moneyPoints + populationPoints);
    }

    public Integer pointsPopulation() {
        return this.project.getLocation().getPopulation() < 2000 ? this.getMoneyDonate() * 2 : 0;
    }

    public Integer pointsMoneyMoreThousand() {
        return this.getMoneyDonate() >= 1000 ? this.getMoneyDonate() : 0;
    }

    public void pointsPerMonthCalender() {
        if (donationsPerMonthCalender() >= 2) {
            this.getUserDonator().giveMePoints(500);
        }
    }

    public Integer donationsPerMonthCalender(){
        return this.getUserDonator().getDonatedProyects().stream().filter(donation -> donation.getDonationMonth().getMonthOfYear() == LocalDate.now().getMonthOfYear()).toArray().length;
    }
}
