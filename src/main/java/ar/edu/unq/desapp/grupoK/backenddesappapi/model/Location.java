package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

public class Location {
    String name;
    String state;
    Integer population;
    Boolean connectivityState;

     Location(String name , String state, Integer population ,Boolean flag ){
        this.name = name;
        this.state = state;
        this.population = population;
        this.connectivityState = flag;
    }

}