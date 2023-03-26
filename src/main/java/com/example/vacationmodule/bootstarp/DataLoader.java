package com.example.vacationmodule.bootstarp;

import com.example.vacationmodule.domain.Role;
import com.example.vacationmodule.domain.User;
import com.example.vacationmodule.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {


        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
        User user1=new User("user1",bcrypt.encode("user1"));
        user1.getRoles().add(Role.ROLE_ADMIN);
        userService.save(user1);

        User user2=new User("user2",bcrypt.encode("user2"));
        user2.getRoles().add(Role.ROLE_USER);
        user2.setVacationDays(25);
        user2.setCountry("Romania");
        userService.save(user2);

    }
}
