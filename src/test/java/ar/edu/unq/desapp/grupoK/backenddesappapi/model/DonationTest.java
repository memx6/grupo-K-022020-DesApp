package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DonationTest {

    private Donation donation;
    private Project project;
    private User user;
    private ArrayList<Donation>donations = new ArrayList<>();

    @BeforeEach
    void setUp () {
        Location location = mock(Location.class);
        ArrayList<Donation> listDonation = mock(ArrayList.class);
        when(location.population()).thenReturn(500);
        project = new Project("avellaneda", listDonation, location, "enero", "agosto", 100, 1000 );
        user = mock(User.class);

        donation = new Donation(project, user,"donation", 500 );

        Donation donation1 = new Donation(project, user,"donation1", 100 );
        Donation donation2 = new Donation(project, user,"donation2", 200 );

        donations.add(donation1);
        donations.add(donation2);
    }

    @Test
    void donationMonthInMonthCalender() {
        assertEquals(donation.donationMonth().getMonth() , LocalDate.now().getMonth());
    }

    @Test
    void pointsReceivedFromATownWithLessThan2000Population() {
        when(project.getLocation().population()).thenReturn(500);
        assertEquals(donation.pointsPopulation() , 1000);
    }
    @Test
    void pointsReceivedFromATownWithMoreThan2000Population() {
        when(project.getLocation().population()).thenReturn(3000);
        assertEquals(donation.pointsPopulation() , 0);
    }

    @Test
    void pointsReceivedForDonationLessThan1000Pesos() {
        assertEquals(donation.pointsMoneyMoreThousand() , 0);
    }

    @Test
    void pointsReceivedForDonationGreaterThan1000Pesos() {
        donation.setMoneyDonate(1000);
        assertEquals(donation.pointsMoneyMoreThousand() , 1000);
    }

    @Test
    void calculatePointsObtainedForDonation() {
        donation.calculatePointsObtained();
        when(user.myPoints()).thenReturn(1600);
        assertEquals(donation.userDonator().myPoints(), 1600);
    }

    @Test
    void pointsPerMonthCalender(){
        donation.pointsPerMonthCalender();
        when(user.myPoints()).thenReturn(500);
        assertEquals(donation.userDonator().myPoints(), 500);
    }

    @Test
    void donationsPerMonthCalender(){
        when(user.getDonatedProyects()).thenReturn(donations);
        assertEquals(donation.donationsPerMonthCalender(), 2);

    }
}


