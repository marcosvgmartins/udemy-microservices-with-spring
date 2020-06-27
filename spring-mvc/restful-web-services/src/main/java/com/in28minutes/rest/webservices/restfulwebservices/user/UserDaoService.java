package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * This annotation means that this is a bean, and Spring becomes aware of it
 */
@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<User>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "Marcos", new Date()));
        users.add(new User(2, "Livia", new Date()));
        users.add(new User(3, "Pepper", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (null == user.getId()) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        User retrieved = users.stream()
            .filter(user -> user.getId().equals(id))
            .findAny()
            .orElse(null);

        if (null == retrieved) {
            throw new UserNotFoundException(id);
        }

        return retrieved;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(id)) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }
}