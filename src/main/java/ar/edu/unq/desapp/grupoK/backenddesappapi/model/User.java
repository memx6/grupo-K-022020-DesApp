package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDonatedMoney;

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

    public User() {}

    public User(String name,
         String pass,
         String nick,
         String email){

            this.name = name;
            this.pass = pass;
            this.nick = nick;
            this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName(){return this.name;}
    public String getNick(){return this.nick;}
    public String getPass(){return this.pass;}
    public String getEmail(){return this.email;}
    public Integer getMyPoints(){
        return this.points;
    }
    public Integer getDonatedMoney(){
        return this.donatedMoney;
    }

    public void setName(String name){this.name = name ;}
    public void setNick(String nick){this.nick = nick;}
    public void setPass(String pass){this.pass = pass;}
    public void setEmail(String email){this.email = email;}
    public void setMyPoints(Integer points){
        this.points = points;
    }
    public void setDonatedMoney(Integer donatedMoney){
        this.donatedMoney = donatedMoney;
    }

    public void donate (Project project, Integer money, String description) throws InvalidDonatedMoney {
        Donation donation = new Donation(project,this,description,money);
        donation.executeDonation();
        this.donatedMoney += money;
        this.donatedProyects.add(donation);
    }

    public void giveMePoints(Integer points) {
        this.points += points;
    }

    public List<Donation> getDonatedProyects () {
        return this.donatedProyects;
    }
}
