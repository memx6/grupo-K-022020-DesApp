package ar.edu.unq.desapp.grupoK.backenddesappapi.services;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMailForDonated(List<User> users, Project project) {
        for (User user : users) {
            mailForDonated(user, project);
        }
    }
    public void mailForDonated(User user, Project project) {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mailMessage, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Notify News");
            helper.setText("<html>"
                        + "<body>"
                        + "<div>"
                        + "<div> " + project.getName() + " is close , thanks " + user.getName() + " for your colaboration. </div>"
                        + "</div>"
                        + "</body>"
                        + "</html>", true);
            javaMailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendTop10Donations(List<String> projectsNames, List<User> users) {
        for (User user : users) {
            emailTop10Donations(user, projectsNames);
        }
    }

    public void sendTopThe10LeastChosenLocations(List<String> locationsNames, List<User> users) {
        for (User user : users) {
            emailTop10LocationsADonor(user, locationsNames);
        }
    }

    public void emailTop10LocationsADonor(User user, List<String> locationsNames){
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mailMessage, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Top 10 Least Chosen Locations");
            helper.setText("<html>"
                    + "<body>"
                    + "<div>"
                    + "<div> " + user.getName() + " see the top 10 locations with fewer donations and collaborate " + locationsNames + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>", true);
            javaMailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void emailTop10Donations(User user, List<String> projectsNames){
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mailMessage, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Top 10 Donations");
            helper.setText("<html>"
                    + "<body>"
                    + "<div>"
                    + "<div> " + user.getName() + " These are the 10 projects with the most donations received " + projectsNames + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>", true);
            javaMailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}