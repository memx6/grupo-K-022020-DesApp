package ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto;

public class DTOAdmin {
    private String password;
    private String email;

    public DTOAdmin() {}

    public void setPassword(String pass) { this.password = pass; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() { return this.email; }
}
