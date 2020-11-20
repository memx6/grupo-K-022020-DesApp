package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoK.backenddesappapi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.UserService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder.UserBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testUserServiceFindAll() {
        MockitoAnnotations.initMocks(this);
        List<User> users = new ArrayList<>();
        users.add(UserBuilder.userwithName("Mauro").build());
        users.add(UserBuilder.userwithName("Fede").build());
        when(userRepository.findAll()).thenReturn(users);
        List<User> usersRecovered = userService.findAll();
        assertEquals(2, usersRecovered.size());
        assertEquals(users, usersRecovered);
    }

    @Test
    public void testUserServiceFindByEmail() {
        MockitoAnnotations.initMocks(this);
        User user = UserBuilder.userwithName("Pepita").build();
        when(userRepository.findByName("Pepita")).thenReturn(user);
        assertEquals(user, userService.findByName("Pepita"));
    }

    /*@Test
    public void it_should_find_user_byEmail() {
        User user = new User();
        user.setEmail("testmail@test.com");
        user = entityManager.persistAndFlush(user);
        assertThat(sut.findByEmail(user.getEmail()).get()).isEqualTo(user);
    }*/
}