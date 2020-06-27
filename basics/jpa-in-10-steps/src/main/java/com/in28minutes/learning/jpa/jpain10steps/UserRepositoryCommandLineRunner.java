package com.in28minutes.learning.jpa.jpain10steps;

import java.util.List;
import java.util.Optional;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;
import com.in28minutes.learning.jpa.jpain10steps.service.UserDaoService;
import com.in28minutes.learning.jpa.jpain10steps.service.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The CommandLineRunners are executed when the Spring Context is created
 */
@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        /**
         * Up until now, now database has been explicitly created
         *
         */
        User user = new User("John", "Admin");
        userRepository.save(user);
        LOGGER.info("New user created: " + user);

        Optional<User> found = userRepository.findById(1l);
        LOGGER.info("Found user: " + found);
        found.get().setRole("Developer");
        userRepository.save(found.get());

        List<User> users = userRepository.findAll();
        LOGGER.info("All users: " + users);
    }
}