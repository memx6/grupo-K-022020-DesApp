package ar.edu.unq.desapp.grupoK.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.EmailService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.ProjectService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
@Controller
public class EmailSenderController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Scheduled(fixedRate = 86400000)
    public void sendEmail() {
        List<User> users = userService.findAll();
        List<String> donations = projectService.top10Donations();
        List<String> locations = projectService.topThe10LeastChosenLocations();

        emailService.sendTopThe10LeastChosenLocations(locations, users);
        emailService.sendTop10Donations(donations, users);
    }
}
