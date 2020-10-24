package ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto;


import org.joda.time.LocalDate;

public class DTOProject {
    private Integer idUserAdmin;
    private Integer idProject;
    private String projectName;
    private Integer minimumClosingPercentage;
    private LocalDate dateEnd;
    private Integer factor;
    private String locationName;

    public DTOProject() {}

    public void setIdUserAdmin(Integer idAdmin) {
        this.idUserAdmin = idAdmin;
    }
    public void setProjectName(String name) {
        this.projectName = name;
    }
    public void setMinimumClosingPercentage(Integer minPercentage) {
        this.minimumClosingPercentage = minPercentage;
    }
    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
    public void setFactor(Integer factor) {
        this.factor = factor;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }
    public Integer getIdUserAdmin() {
        return idUserAdmin;
    }
    public String getProjectName() {
        return projectName;
    }
    public Integer getMinimumClosingPercentage() { return minimumClosingPercentage; }
    public LocalDate getDateEnd() {
        return dateEnd;
    }
    public Integer getFactor() {
        return factor;
    }
    public String getLocationName() {
        return locationName;
    }
    public Integer getIdProject() {
        return idProject;
    }
}
