package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDonatedMoney;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    protected String name;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 4, max = 8)
    protected String password;
    @NotBlank(message = "Nickname is mandatory")
    @Size(min = 4, max = 6)
    protected String nick;
    protected Integer points = 0;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter a correct email")
    protected String email;
    protected Integer donatedMoney = 0;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    protected List<Donation> donatedProyects = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public User() {}

    public User(String name,
         String password,
         String nick,
         String email){

            this.name = name;
            this.password = password;
            this.nick = nick;
            this.email = email;
            this.rol = Rol.ROLE_USER;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName(){return this.name;}
    public String getNick(){return this.nick;}
    public String getPassword(){return this.password;}
    public String getEmail(){return this.email;}
    public Integer getMyPoints(){
        return this.points;
    }
    public Integer getDonatedMoney(){
        return this.donatedMoney;
    }

    public void setName(String name){this.name = name ;}
    public void setNick(String nick){this.nick = nick;}
    public void setPassword(String pass){this.password = pass;}
    public void setEmail(String email){this.email = email;}
    public void setMyPoints(Integer points){
        this.points = points;
    }
    public void setDonatedMoney(Integer donatedMoney){
        this.donatedMoney = donatedMoney;
    }

    public Donation donate (Project project, Integer money, String description) throws InvalidDonatedMoney {
        Donation donation = new Donation(project,this,description,money);
        donation.executeDonation();
        this.donatedMoney += money;
        this.donatedProyects.add(donation);
        return donation;
    }

    public void giveMePoints(Integer points) {
        this.points += points;
    }

    public List<Donation> getDonatedProyects () {
        return this.donatedProyects;
    }

    public Enum<Rol> rol(){
        return this.rol;
    }
}
