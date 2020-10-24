package ar.edu.unq.desapp.grupoK.backenddesappapi.services;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.FactorInvalid;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDateEndForProject;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidMinPercent;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.DonationBuilder;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.LocationBuilder;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.ProjectBuilder;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.UserBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;


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
    private UserAdministratorService userAdministratorService;

    @Autowired
    private DonationService donationService;

    @PostConstruct
    public void initialize() throws InvalidMinPercent, FactorInvalid, InvalidDateEndForProject {
        if (className.equals("org.h2.Driver")) {
            logger.info("Init Data Using H2 DB");
            fireInitialDataProject();
            fireInitialDataUser();
            fireInitialDataDonation();
        }
    }

    private void fireInitialDataProject() throws InvalidDateEndForProject, FactorInvalid, InvalidMinPercent {
        Location avellaneda = LocationBuilder.LocationwithName("Avellaneda")
                .withProvince("Buenos Aires")
                .withPopulation(1000)
                .withConnectivityState(true)
                .build();
        Project project1 = ProjectBuilder.projectwithName("ProyectoAvellaneda")
                .withFactor(1000)
                .withDateEnd(LocalDate.parse("2020-12-11"))
                .withLocation(avellaneda)
                .build();

        Location quilmes = LocationBuilder.LocationwithName("Bernal")
                .withProvince("BS AS")
                .withPopulation(1000)
                .withConnectivityState(false)
                .build();
        Project project2 = ProjectBuilder.projectwithName("ProyectoQuilmes")
                .withFactor(1000)
                .withDateEnd(LocalDate.parse("2022-12-11"))
                .withLocation(quilmes)
                .build();

        projectService.save(project1);
        projectService.save(project2);
    }

    private void fireInitialDataUser() {
        User mauro = UserBuilder.userwithName("Mauro")
                .withNick("memm")
                .withPass("1234")
                .withEmail("mem@gmail.com")
                .build();
        User fede = UserBuilder.userwithName("Fede")
                .withNick("fmsa")
                .withPass("4321")
                .withEmail("fms@gmail.com")
                .build();
        UserAdministrator adm = new UserAdministrator ();
        adm.setName("Admin");
        adm.setPassword("4321");
        adm.setNick("admi");
        adm.setEmail("adm@gmail.com");

        userAdministratorService.save(adm);
        userService.save(mauro);
        userService.save(fede);
    }

    private void fireInitialDataDonation() {
        Donation donation = DonationBuilder.donation()
                .withProject(projectService.findAll().get(0))
                .withUser(userService.findAll().get(0))
                .withDescription("AvellanedaDonation")
                .withMoney(1000)
                .withDate(LocalDate.parse("2020-12-11"))
                .build();

        donationService.save(donation);
    }
}
