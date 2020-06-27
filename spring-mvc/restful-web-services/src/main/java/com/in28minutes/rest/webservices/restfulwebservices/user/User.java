package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.in28minutes.rest.webservices.restfulwebservices.post.Post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The user is the main resource of the application")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    // This is a javax.validation constraint
    @Size(min = 2, message = "User name must have at least 2 characters")
    @ApiModelProperty(notes = "Must have at least two characters")
    private String name;

    // This javax.validation constraint ensures that the birth date has
    // occured in the past
    @Past
    @ApiModelProperty(notes = "Birth date must indicate a date in the past")
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {

    }

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("Id = [%d], Name = [%s], Birth = [%s]", id, name, birthDate);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}