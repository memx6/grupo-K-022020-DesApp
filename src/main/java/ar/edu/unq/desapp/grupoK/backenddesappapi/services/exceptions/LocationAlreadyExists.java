package ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions;

public class LocationAlreadyExists extends Exception {
    public LocationAlreadyExists() {
        super("There is already a project with the requested location");
    }
}
