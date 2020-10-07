package ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions;

public class InvalidDateEndForProject extends Exception{
    public InvalidDateEndForProject() {super("Invalid end date. The Project end date must be after the start date");}
}
