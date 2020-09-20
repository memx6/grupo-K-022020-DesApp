package ar.edu.unq.desapp.grupoK.backenddesappapi.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
    void setUp () {
        location = mock(Location.class);
        when(location.population()).thenReturn(1500);
        user = mock(User.class);
        project = new Project("avellaneda", location, LocalDate.of(2020,12,11),100, 1000);
        donation = new Donation(project,user,"Suerte",1000);
    }

    @Test
    public void gettersValidInNewProject() {
        assertEquals(project.getName(), "avellaneda");
        assertEquals(project.getMinimumClosingPercentage(), 100);
        assertEquals(project.getFactor(), 1000);
        assertEquals(project.getLocation(), location);
        assertEquals(project.getDateEnd(), LocalDate.of(2020, 12, 11));
    }

    @Test
    public void testMoneyNeededForProject() {
        assertEquals(project.moneyNeededForProject().intValue() , 1500000);
    }

    @Test
    public void setFactorInProject() {
        project.setFactor(50);

        assertEquals(project.getFactor() , 50);
    }

    @Test
    public void theMinimumValidPercentageSettingIsTested() {
        project.setMinimumClosingPercentage(50);
        assertEquals(project.getMinimumClosingPercentage(), 50);
    }

    @Test
    public void percentageSettingInvalidIsTested()  {
        assertThrows(IllegalArgumentException.class, () -> project.setMinimumClosingPercentage(40));
    }

    @Test
    public void moneyReceiveForProject() {
        project.addMoney(500);
        assertEquals(project.moneyReceiveForProject(), 500);
    }

    @Test
    public void testReceiveDonation() {

        project.receiveDonation(donation);

        assertEquals(project.allDonations().size() , 1);
        assertEquals(project.moneyReceiveForProject().intValue() , 1000);
    }

    @Test
    public void testUpAndDownProject() {
        assertEquals(project.activeProject(), true);
        project.downProject();
        assertEquals(project.activeProject(), false);
    }
}
