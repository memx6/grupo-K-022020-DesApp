package ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Donation;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserBuilder {
    protected String name;
    protected String pass;
    protected String nick;
    protected Integer points = 0;
    protected String email;
    protected Integer donatedMoney = 0;
    protected List<Donation> donatedProyects = new ArrayList<>();

    public static UserBuilder userwithName(String name) {
        UserBuilder builder = new UserBuilder();
        builder.name = name;
        return builder;
    }

    public User build() {
        return new User(name, pass, nick, email);
    }

    public UserBuilder withPass(String pass) {
        this.pass = pass;
        return this;
    }

    public UserBuilder withNick(String nick) {
        this.nick = nick;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

}
