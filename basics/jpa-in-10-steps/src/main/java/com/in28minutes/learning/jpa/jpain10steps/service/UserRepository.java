package com.in28minutes.learning.jpa.jpain10steps.service;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * With Spring Data JPA, all we have to do is extend the Spring interface
 * and Spring will take care of generating the implementation for us.
 *
 * This avoids code duplication as the number of entities increases
 */
public interface UserRepository extends JpaRepository<User, Long> {

}