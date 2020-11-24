package ar.edu.unq.desapp.grupoK.backenddesappapi.model;


import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.*;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectTest {

    Project project;
    User user ;
    Location location;
    Donation donation;

    @BeforeEach
    void setUp () throws InvalidMinPercent, FactorInvalid, InvalidDateEndForProject {
        location = mock(Location.class);
        when(location.getPopulation()).thenReturn(1500);
        when(location.getProvince()).thenReturn("Buenos Aires");
        when(location.getConnectivityState()).thenReturn(false);
        user = mock(User.class);
        project = new Project("avellaneda", location, LocalDate.parse("2020-12-11"),100, 1000);
        donation = new Donation(project,user,"Suerte" +
                "",1000);
    }

    @Test
    public void gettersValidInNewProject() {
        assertEquals(project.getName(), "avellaneda");
        assertEquals(project.getMinimumClosingPercentage(), 100);
        assertEquals(project.getFactor(), 1000);
        assertEquals(project.getMoneyReceiveForProject(), 0);
        assertEquals(project.getLocation(), location);
        project.setVisibility(true);
        assertEquals(project.getVisibility(), true);
        assertEquals(project.getPercentageCompleted(), 0);
        assertEquals(project.getDateEnd(), LocalDate.parse("2020-12-11"));
    }

    @Test
    public void testMoneyNeededForProject() {
        assertEquals(project.moneyNeededForProject().intValue() , 1500000);
    }

    @Test
    public void setFactorInProject() throws FactorInvalid {
        project.setFactor(50);

        assertEquals(project.getFactor() , 50);
    }

    @Test
    public void theMinimumValidPercentageSettingIsTested() throws InvalidMinPercent {
        project.setMinimumClosingPercentage(50);
        assertEquals(project.getMinimumClosingPercentage(), 50);
    }

    @Test
    public void percentageSettingInvalidIsTested()  {
        try {
            project.setMinimumClosingPercentage(40);
        }catch (InvalidMinPercent e) {
            assertEquals("The percentage is not within the allowed range, choose a range between 50 and 100", e.getMessage());
        }
    }

    @Test
    public void factorInvalidIsTested()  {
        try {
            project.setFactor(-1);
        }catch (FactorInvalid e) {
            assertEquals("Factor Invalid. Project factor between 0 and 100000.", e.getMessage());
        }
    }

    @Test
    public void moneyReceiveForProject() throws InvalidDonatedMoney {
        project.addMoney(500);
        assertEquals(project.moneyReceiveForProject(), 500);
    }
    @Test
    public void moneyDonatedInvalidIsTested()  {
        try {
            project.addMoney(-1);
        }catch (InvalidDonatedMoney e) {
            assertEquals("The amount donated cannot be less than or equal to 0", e.getMessage());
        }
    }

    @Test
    public void setDateEndValidIsTested() throws InvalidDateEndForProject {
        project.setDateEnd(LocalDate.parse("2020-12-11"));

        assertEquals(project.getDateEnd(), LocalDate.parse("2020-12-11"));
    }
    @Test
    public void setDateEndInvalidIsTested()  {
        try {
            project.setDateEnd(LocalDate.parse("2019-12-11"));
        }catch (InvalidDateEndForProject e) {
            assertEquals("Invalid end date. The Project end date must be after the start date", e.getMessage());
        }
    }
    @Test
    public void testReceiveDonation() throws InvalidDonatedMoney {

        project.receiveDonation(donation);

        assertEquals(project.getDonations().size() , 1);
        assertEquals(project.moneyReceiveForProject().intValue() , 1000);
    }

    @Test
    public void testUpAndDownProject() {
        assertEquals(project.activeProject(), true);
        project.downProject();
        when(location.getConnectivityState()).thenReturn(true);
        assertEquals(project.activeProject(), false);
        assertEquals(project.getLocation().getConnectivityState(), true);

    }
}
