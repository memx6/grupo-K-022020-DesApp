package ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto;

public class DTODonation {

    private Integer idUser;
    private Integer idProject;
    private Integer moneyDonated;
    private String description;

    public DTODonation() {}

    public void setIdUser(Integer idUser) { this.idUser = idUser; }

    public void setIdProject(Integer idProject) { this.idProject = idProject; }

    public void setMoneyDonated(Integer moneyDonated) { this.moneyDonated = moneyDonated; }

    public void setDescription(String description) { this.description = description; }

    public Integer getIdUser() { return this.idUser; }

    public Integer getIdProject() { return this.idProject; }

    public Integer getMoneyDonated() { return this.moneyDonated; }

    public String getDescription() { return this.description; }
}
