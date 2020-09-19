package ar.edu.unq.desapp.grupoK.backenddesappapi.model;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectTest {

    Project project;
    ArrayList<Donation> donations = new ArrayList<Donation>();
    User user ;

    Donation d1;
    @BeforeEach
    void setUp () {
        Location location = mock(Location.class);
        when(location.population()).thenReturn(1500);
        user = mock(User.class);
        project = new Project("avellaneda", donations, location, "enero", "agosto", 100, 1000 );
        d1 = new Donation(project,user,"Suerte",1000);


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

        project.receiveDonation(d1);

        assertEquals(project.allDonations().size() , 1);
        assertEquals(project.moneyReceiveForProject().intValue() , 1000);
    }


}
