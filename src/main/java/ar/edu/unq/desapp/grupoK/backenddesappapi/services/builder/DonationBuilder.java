package ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Donation;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;

import java.time.LocalDate;

public class DonationBuilder {
    private Project project;
    private User user;
    private String description;
    private Integer money;
    private LocalDate dateTrx;

    public DonationBuilder() {}

    public static DonationBuilder donation(){
        return new DonationBuilder();
    }

    public Donation build() {
        return new Donation(project, user, description, money);
    }

    public DonationBuilder withProject(Project givenProject) {
        this.project = givenProject;
        return this;
    }

    public DonationBuilder withUser(User givenUser) {
        this.user = givenUser;
        return this;
    }

    public DonationBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public DonationBuilder withMoney(Integer givenMoney) {
        this.money = givenMoney;
        return this;
    }

    public DonationBuilder withDate(LocalDate dateTrx) {
        this.dateTrx = dateTrx;
        return this;
    }
}
