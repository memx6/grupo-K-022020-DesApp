package ar.edu.unq.desapp.grupoK.backenddesappapi.services;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Donation;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.DonationBuilder;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.LocationBuilder;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.ProjectBuilder;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.UserBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class InitServiceInMemory {

    protected final Log logger = LogFactory.getLog(getClass());

    @Value("${spring.datasource.driverClassName:NONE}")
    private String className;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private DonationService donationService;

    @PostConstruct
    public void initialize() {
        if (className.equals("org.h2.Driver")) {
            logger.info("Init Data Using H2 DB");
            fireInitialDataProject();
            fireInitialDataUser();
            fireInitialDataDonation();
        }
    }

    private void fireInitialDataProject() {
        Location avellaneda = LocationBuilder.LocationwithName("Avellaneda")
                .withPopulation(1000)
                .build();
        Project project1 = ProjectBuilder.projectwithName("ProyectoAvellaneda")
                .withFactor(1000)
                .withDateEnd(LocalDate.of(2022,12,11))
                .withLocation(avellaneda)
                .build();

        Location quilmes = LocationBuilder.LocationwithName("Bernal")
                .withPopulation(1000)
                .build();
        Project project2 = ProjectBuilder.projectwithName("ProyectoQuilmes")
                .withFactor(1000)
                .withDateEnd(LocalDate.of(2022,12,11))
                .withLocation(quilmes)
                .build();

        projectService.save(project1);
        projectService.save(project2);
    }

    private void fireInitialDataUser() {
        User mauro = UserBuilder.userwithName("Mauro")
                .withNick("mem")
                .withPass("123")
                .withEmail("mem@gmail.com")
                .build();
        User fede = UserBuilder.userwithName("Fede")
                .withNick("fms")
                .withPass("321")
                .withEmail("fms@gmail.com")
                .build();

        userService.save(mauro);
        userService.save(fede);
    }

    private void fireInitialDataDonation() {
        Donation donation = DonationBuilder.donation()
                .withProject(projectService.findAll().get(0))
                .withUser(userService.findAll().get(0))
                .withDescription("AvellanedaDonation")
                .withMoney(1000)
                .withDate(LocalDate.of(2020, 12, 11))
                .build();

        donationService.save(donation);
    }
}
