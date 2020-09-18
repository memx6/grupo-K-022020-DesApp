package ar.edu.unq.desapp.grupoK.backenddesappapi.model;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProyectTest {

    @BeforeEach
    void setUp () {

    }
    @Test
    public void testMoneyNeededForProject() throws Exception {
        Location location = mock(Location.class);
        when(location.population()).thenReturn(1500);
        ArrayList<Donation> listDonation = null;
        Proyect proyect = new Proyect("avellaneda", listDonation, location, "enero", "agosto", 100, 1000 );

        assertEquals(proyect.moneyNeededForProject().intValue() , 1500000);

    }

    @Test
    public void testReceiveDonation() throws Exception {
        Location location = mock(Location.class);
        ArrayList<Donation> listDonation = mock(ArrayList.class);
        Proyect proyect = new Proyect("avellaneda", listDonation, location, "enero", "agosto", 100, 1000 );

        Donation donation = mock(Donation.class);
        when(donation.moneyDonate()).thenReturn(1000);
        proyect.receiveDonation(donation);

        //assertEquals(proyect.allDonations().size() , 1);
        assertEquals(proyect.moneyReceiveForProject().intValue() , 1000);

    }

}
