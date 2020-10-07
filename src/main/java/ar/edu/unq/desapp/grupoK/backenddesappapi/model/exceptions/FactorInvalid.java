package ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions;

public class FactorInvalid extends Exception {
    public FactorInvalid() { super("Factor Invalid. Project factor between 0 and 100000."); }
}
