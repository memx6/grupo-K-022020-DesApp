package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

public class Location {
    String name;
    String province;
    Integer population;
    Boolean connectivityState;

     Location(String name, String province, Integer population, Boolean state ){
        this.name = name;
        this.province = province;
        this.population = population;
        this.connectivityState = state;
    }


    public Integer population() {
        return this.population;
    }
}
