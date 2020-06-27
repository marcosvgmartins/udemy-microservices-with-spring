package com.in28minutes.learning.jpa.jpain10steps;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;
import com.in28minutes.learning.jpa.jpain10steps.service.UserDaoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The CommandLineRunners are executed when the Spring Context is created
 */
@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserDaoService userDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        /**
         * Up until now, now database has been explicitly created
         *
         */
        User user = new User("Jack", "Admin");
        userDao.insert(user);
        LOGGER.info("New user created: " + user);
    }
}