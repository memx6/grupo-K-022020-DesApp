package ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions;

public class CantFinishProject extends Exception {
    public CantFinishProject() { super("The project you are trying to finish does not meet the conditions."); }
}
