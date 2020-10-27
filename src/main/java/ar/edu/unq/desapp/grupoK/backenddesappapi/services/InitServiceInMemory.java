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
        Project projectAvellaneda = ProjectBuilder.projectwithName("Proyecto Avellaneda")
                .withFactor(1000)
                .withDateEnd(LocalDate.parse("2020-12-11"))
                .withLocation(avellaneda)
                .build();

        Location quilmes = LocationBuilder.LocationwithName("Bernal")
                .withProvince("BS AS")
                .withPopulation(1000)
                .withConnectivityState(false)
                .build();
        Project projectQuilmes = ProjectBuilder.projectwithName("Proyecto Quilmes")
                .withFactor(1000)
                .withDateEnd(LocalDate.parse("2022-11-11"))
                .withLocation(quilmes)
                .build();

        Location rioCuarto = LocationBuilder.LocationwithName("Rio Cuarto")
                .withProvince("Cordoba")
                .withPopulation(2000)
                .withConnectivityState(false)
                .build();
        Project projectCordoba = ProjectBuilder.projectwithName("Proyecto Rio Cuarto")
                .withFactor(1000)
                .withDateEnd(LocalDate.parse("2021-08-11"))
                .withLocation(rioCuarto)
                .build();

        Location calafate = LocationBuilder.LocationwithName("Calafate")
                .withProvince("Santa Cruz")
                .withPopulation(1700)
                .withConnectivityState(false)
                .build();
        Project projectCalafate = ProjectBuilder.projectwithName("Proyecto Calafate")
                .withFactor(1000)
                .withDateEnd(LocalDate.parse("2021-4-11"))
                .withLocation(calafate)
                .build();

        Location ushuaia = LocationBuilder.LocationwithName("Ushuaia")
                .withProvince("Tierra del Fuego")
                .withPopulation(1500)
                .withConnectivityState(false)
                .build();
        Project projectUshuaia = ProjectBuilder.projectwithName("Proyecto Ushuaia")
                .withFactor(1000)
                .withDateEnd(LocalDate.parse("2022-12-11"))
                .withLocation(ushuaia)
                .build();

        projectService.save(projectAvellaneda);
        projectService.save(projectQuilmes);
        projectService.save(projectCordoba);
        projectService.save(projectCalafate);
        projectService.save(projectUshuaia);
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
        User german = UserBuilder.userwithName("German")
                .withNick("mang")
                .withPass("1234")
                .withEmail("german@gmail.com")
                .build();
        User jose = UserBuilder.userwithName("Jose")
                .withNick("jose")
                .withPass("4321")
                .withEmail("josesito@gmail.com")
                .build();
        User pepita = UserBuilder.userwithName("Pepita")
                .withNick("pepi")
                .withPass("1234")
                .withEmail("pepi@gmail.com")
                .build();
        User pepon = UserBuilder.userwithName("Pepon")
                .withNick("pepo")
                .withPass("4321")
                .withEmail("pepon@gmail.com")
                .build();

        User fidel = UserBuilder.userwithName("Fidel")
                .withNick("Fidel")
                .withPass("haskell")
                .withEmail("foldr@gmail.com")
                .build();

        UserAdministrator adm = new UserAdministrator ();
        adm.setName("Admin");
        adm.setPassword("4321");
        adm.setNick("admi");
        adm.setEmail("adm@gmail.com");

        userAdministratorService.save(adm);
        userService.save(mauro);
        userService.save(fede);
        userService.save(german);
        userService.save(jose);
        userService.save(pepita);
        userService.save(pepon);
        userService.save(fidel);
    }

    private void fireInitialDataDonation() {
        Donation donation1 = DonationBuilder.donation()
                .withProject(projectService.findAll().get(0))
                .withUser(userService.findByName("Mauro"))
                .withDescription("Avellaneda Donation")
                .withMoney(1000)
                .withDate(LocalDate.parse("2020-12-09"))
                .build();

        Donation donation2 = DonationBuilder.donation()
                .withProject(projectService.findAll().get(0))
                .withUser(userService.findByName("Fede"))
                .withDescription("Avellaneda Donation")
                .withMoney(1000)
                .withDate(LocalDate.parse("2022-11-08"))
                .build();
        Donation donation3 = DonationBuilder.donation()
                .withProject(projectService.findAll().get(0))
                .withUser(userService.findByName("German"))
                .withDescription("Avellaneda Donation")
                .withMoney(1000)
                .withDate(LocalDate.parse("2020-12-10"))
                .build();
        Donation donation4 = DonationBuilder.donation()
                .withProject(projectService.findAll().get(1))
                .withUser(userService.findByName("Mauro"))
                .withDescription("Quilmes Donation")
                .withMoney(1000)
                .withDate(LocalDate.parse("2021-12-11"))
                .build();
        Donation donation5 = DonationBuilder.donation()
                .withProject(projectService.findAll().get(1))
                .withUser(userService.findByName("Pepita"))
                .withDescription("Quilmes Donation")
                .withMoney(1000)
                .withDate(LocalDate.parse("2021-12-11"))
                .build();
        Donation donation6 = DonationBuilder.donation()
                .withProject(projectService.findAll().get(4))
                .withUser(userService.findByName("Fede"))
                .withDescription("Calafate Donation")
                .withMoney(1000)
                .withDate(LocalDate.parse("2020-12-11"))
                .build();
        Donation donation7 = DonationBuilder.donation()
                .withProject(projectService.findAll().get(4))
                .withUser(userService.findByName("Jose"))
                .withDescription("Calafate Donation")
                .withMoney(1000)
                .withDate(LocalDate.parse("2020-08-11"))
                .build();
        Donation donation8 = DonationBuilder.donation()
                .withProject(projectService.findAll().get(3))
                .withUser(userService.findByName("Pepon"))
                .withDescription("Rio Cuarto Donation")
                .withMoney(1000)
                .withDate(LocalDate.parse("2021-04-11"))
                .build();

        donationService.save(donation1);
        donationService.save(donation2);
        donationService.save(donation3);
        donationService.save(donation4);
        donationService.save(donation5);
        donationService.save(donation6);
        donationService.save(donation7);
        donationService.save(donation8);
    }
}