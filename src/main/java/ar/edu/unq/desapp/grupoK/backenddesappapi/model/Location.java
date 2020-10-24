package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Province is mandatory")
    private String province;
    @NotNull(message = "Population is mandatory")
    @Min(100)
    private Integer population;
    private boolean connectivityState;

    public Location() {super();}

    public Location(String name, String province, Integer population, boolean state ){
        this.name = name;
        this.province = province;
        this.population = population;
        this.connectivityState = state;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public String getProvince() {
        return this.province;
    }
    public Integer getPopulation() {
        return this.population;
    }
    public boolean getConnectivityStat() {
        return this.connectivityState;
    }
    public void setConnectivityStat(boolean state) {
        this.connectivityState = state;
    }
}
