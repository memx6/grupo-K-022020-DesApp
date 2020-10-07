package ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions;

public class InvalidMinPercent extends Exception {
    public InvalidMinPercent() {super("The percentage is not within the allowed range, choose a range between 50 and 100");}
}
