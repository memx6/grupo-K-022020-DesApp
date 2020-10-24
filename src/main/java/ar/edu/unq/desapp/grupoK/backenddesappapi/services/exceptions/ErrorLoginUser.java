package ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions;

public class ErrorLoginUser extends Exception {
    public ErrorLoginUser() {
        super("The password and / or your email are incorrect");
    }
}
