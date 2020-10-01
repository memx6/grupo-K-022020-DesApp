package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String province;
    private Integer population;
    private Boolean connectivityState;

    public Location() {super();}

    public Location(String name, String province, Integer population, Boolean state ){
        this.name = name;
        this.province = province;
        this.population = population;
        this.connectivityState = state;
    }

    public Integer population() {
        return this.population;
    }
}
