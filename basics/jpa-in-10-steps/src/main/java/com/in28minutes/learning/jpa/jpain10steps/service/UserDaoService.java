package com.in28minutes.learning.jpa.jpain10steps.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;

import org.springframework.stereotype.Repository;

/**
 * Data Access Object for Users
 */
@Repository
// "Transactional will be applied to all methods"
@Transactional
public class UserDaoService {

    /**
     * To be able to access the database, we need an EntityManager
     *
     * */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Saves a user to the database
     */
    public long insert(User user) {
        /**
         * When "persist" is invoked, the Persistence Context becomes aware of this entity
         */
        entityManager.persist(user);
        return user.getId();
    }
}