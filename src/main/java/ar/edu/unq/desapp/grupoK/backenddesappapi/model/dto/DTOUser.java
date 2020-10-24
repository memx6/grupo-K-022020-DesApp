package ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto;

public class DTOUser {
    private String password;
    private String email;

    public DTOUser() {}

    public void setPassword(String pass) { this.password = pass; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() { return this.email; }
}
