package ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions;

public class MustBeAPositive extends Exception {
    public MustBeAPositive() {
        super("Invalid integer value provided. Must be a positive value.");
    }
    public MustBeAPositive(String errorMessage) {
        super(errorMessage);
    }
}

