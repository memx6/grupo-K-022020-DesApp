package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    protected String name;
    protected String pass;
    protected String nick;
    protected Integer points = 0;
    protected String email;
    protected Integer donatedMoney = 0;

    @OneToMany(mappedBy = "user")
    protected List<Donation> donatedProyects = new ArrayList<>();

    public User() {super();}

    public User(String name,
         String pass,
         String nick,
         String email){

            this.name = name;
            this.pass = pass;
            this.nick = nick;
            this.email = email;
    }

    public void donate (Project project, Integer money, String description){
        Donation donation = new Donation(project,this,description,money);
        donation.executeDonation();
        this.donatedMoney += money;
        this.donatedProyects.add(donation);
    }

    public Integer myPoints(){
        return this.points;
    }

    public void giveMePoints(Integer points) {
        this.points += points;
    }

    public List<Donation> getDonatedProyects () {
        return this.donatedProyects;
    }

    public String getName(){
        return this.name;
    }

}
