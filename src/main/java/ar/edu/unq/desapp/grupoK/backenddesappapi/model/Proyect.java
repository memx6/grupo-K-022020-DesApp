package ar.edu.unq.desapp.grupoK.backenddesappapi.model;
import java.util.*;

public class Proyect {
    String proyectName;
    ArrayList<User> donor;
    Location location;
    String dateEnd ;
    String dateStart;
    Integer percentage;
    Integer factorStart;
    Integer factorEnd;

    Proyect(String name , ArrayList<User> donor, Location location , String dateEnd ,String dateStart ,Integer percentage ,Integer factorStart,Integer factorEnd){
            this.proyectName = name;
            this.donor = donor;
            this.location = location;
            this.dateEnd = dateEnd;
            this.dateStart = dateStart;
            this.percentage = percentage;
            this.factorStart = factorStart;
            this.factorEnd = factorEnd;
    }
    
}