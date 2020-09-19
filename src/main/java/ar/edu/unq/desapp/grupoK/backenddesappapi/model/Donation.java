package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.time.LocalDate;

public class Donation {
    Project project;
    User user;
    String description;
    Integer money;
    LocalDate dateTrx;

    Donation(Project project, User user, String description, Integer money) {
        this.project = project;
        this.user = user;
        this.description = description;
        this.money = money;
        this.dateTrx = LocalDate.now();
    }

    public LocalDate donationMonth(){
        return this.dateTrx;
    }
    public User userDonator() {
        return this.user;
    }

    public void setMoneyDonate(Integer money) {
        this.money = money;
    }

    public Integer moneyDonate() {
        return this.money;
    }

    public Integer getPoints() {
        return this.user.myPoints();
    }

    public void executeDonation(){
        this.calculatePointsObtained();
        this.project.receiveDonation(this);
    }

    public void calculatePointsObtained() {
        Integer moneyPoints = this.pointsMoneyMoreThousand();
        Integer populationPoints = this.pointsPopulation();
        this.pointsPerMonthCalender();
        this.userDonator().giveMePoints(moneyPoints + populationPoints);
    }

    public Integer pointsPopulation() {
        return this.project.getLocation().population() < 2000 ? this.moneyDonate() * 2 : 0;
    }

    public Integer pointsMoneyMoreThousand() {
        return this.moneyDonate() >= 1000 ? this.moneyDonate() : 0;
    }

    public void pointsPerMonthCalender() {
        if (donationsPerMonthCalender() >= 2) {
            this.userDonator().giveMePoints(500);
        }
    }

    public Integer donationsPerMonthCalender(){
        return this.userDonator().getDonatedProyects().stream().filter(donation -> donation.donationMonth().getMonth() == LocalDate.now().getMonth()).toArray().length;
    }


}
