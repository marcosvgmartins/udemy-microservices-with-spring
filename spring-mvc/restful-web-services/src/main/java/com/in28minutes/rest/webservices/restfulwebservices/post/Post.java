package com.in28minutes.rest.webservices.restfulwebservices.post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    // JsonIgnore to avoid an infinite loop
    @JsonIgnore
    private User user;

    public Post() {

    }

    public Post(Integer id, String content, User user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("Post Id = [%d] | Content = [%s]", id, content);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}