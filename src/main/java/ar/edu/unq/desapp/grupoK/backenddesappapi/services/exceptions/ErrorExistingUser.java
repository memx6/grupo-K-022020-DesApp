package ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions;

public class ErrorExistingUser extends Exception {

    public ErrorExistingUser(String email) {
        super("your email " + email + " is already registered");
    }
}
