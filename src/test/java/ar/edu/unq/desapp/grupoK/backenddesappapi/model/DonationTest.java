package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        when(location.population()).thenReturn(500);
        project = new Project("avellaneda", location, LocalDate.of(2020,12,11), 100, 1000);
        user =  new User("Federico","pepito","Icardi","fedeericosanchez18@gmail.com");

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
        assertEquals(donation.getPoints(), 1000);
    }

    @Test
    void pointsPerMonthCalender(){
        user.donate(project,100,"uno");
        user.donate(project,200,"dos");
        donation.pointsPerMonthCalender();
        assertEquals(donation.getPoints(), 1100);
    }

    @Test
    void donationsPerMonthCalender(){
        user.donate(project,100,"uno");
        user.donate(project,200,"dos");
        assertEquals(user.getDonatedProyects().size(), 2);
        assertEquals(donation.donationsPerMonthCalender(), 2);
    }
}


