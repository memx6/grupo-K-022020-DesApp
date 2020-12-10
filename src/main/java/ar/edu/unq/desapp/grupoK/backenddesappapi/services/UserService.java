package ar.edu.unq.desapp.grupoK.backenddesappapi.services;


import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Donation;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Project;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto.DTODonation;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.dto.DTOUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.exceptions.InvalidDonatedMoney;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.DonationRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.ErrorExistingUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions.ErrorLoginUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private ProjectService projectService;

    @Transactional
    public User save(User model) {
        return this.userRepository.save(model);
    }

    public User userById(Integer id) {
        return this.userRepository.findById(id).get();
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findByName(String name) {
        return this.userRepository.findByName(name);
    }

    public User create(User user) throws ErrorExistingUser {
        User userSaved = userRepository.findByEmail(user.getEmail());
        if (userSaved != null) {
            throw new ErrorExistingUser(user.getEmail());
        }
        String token = getJWTToken(userSaved.getName());
        userSaved.setToken(token);
        return userRepository.save(user);
    }

    @ExceptionHandler(ErrorLoginUser.class)
    public User login(DTOUser dtoUser) throws ErrorLoginUser {
        User userLogin = userRepository.findByEmail(dtoUser.getEmail());
        if (userLogin == null) {
            throw new ErrorLoginUser();
        }
        if (!(userLogin.getPassword().equals(dtoUser.getPassword()))) {
            throw new ErrorLoginUser();
        }
        String token = getJWTToken(userLogin.getName());
        userLogin.setToken(token);
        return userLogin;
    }

    @ExceptionHandler(InvalidDonatedMoney.class)
    public Donation donate(DTODonation dtoDonation) throws InvalidDonatedMoney {
        User donorUser = userRepository.findById(dtoDonation.getIdUser()).get();
        Project project = projectService.findById(dtoDonation.getIdProject());

        Donation donation = donorUser.donate(project, dtoDonation.getMoneyDonated(), dtoDonation.getDescription());
        donationRepository.save(donation);

        return donation;
    }

    public User userProfile(Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    public List<User> findByDonationsForProject(Integer idProject) {
        return this.userRepository.findByDonationsForProject(idProject);
    }

    public void update(User user) {
        User userRecovered = userRepository.findById(user.getId()).get();
        userRecovered.setName(user.getName());
        userRecovered.setNick(user.getNick());
        userRecovered.setPassword(user.getPassword());
        userRecovered.setEmail(user.getEmail());
        userRepository.save(userRecovered);
    }

    private String getJWTToken(String name) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setSubject(name)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
