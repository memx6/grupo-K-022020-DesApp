package ar.edu.unq.desapp.grupoK.backenddesappapi.model;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectTest {

    private Project project;
    @BeforeEach
    void setUp () {
        Location location = mock(Location.class);
        when(location.population()).thenReturn(1500);
        ArrayList<Donation> listDonation = mock(ArrayList.class);;
        project = new Project("avellaneda", listDonation, location, "enero", "agosto", 100, 1000 );

    }
    @Test
    public void testMoneyNeededForProject() throws Exception {
        assertEquals(project.moneyNeededForProject().intValue() , 1500000);
    }

    @Test
    public void setFactorInProject() throws Exception {
        project.setFactor(50);
        project.setMinimumClosingPercentage(30);
        assertEquals(project.getFactor() , 50);
        assertEquals(project.getMinimumClosingPercentage(), 30);
    }

    @Test
    public void moneyReceiveForProject() throws Exception {
        project.addMoney(500);
        assertEquals(project.moneyReceiveForProject(), 500);
    }

    @Test
    public void testReceiveDonation() throws Exception {
        Donation donation = mock(Donation.class);
        when(donation.moneyDonate()).thenReturn(1000);
        project.receiveDonation(donation);

        //assertEquals(project.allDonations().size() , 1);
        assertEquals(project.moneyReceiveForProject().intValue() , 1000);
    }


}
