package ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions;

public class InvalidDonatedMoney extends Exception {
    public InvalidDonatedMoney () {super("The amount donated cannot be less than or equal to 0");}
}
