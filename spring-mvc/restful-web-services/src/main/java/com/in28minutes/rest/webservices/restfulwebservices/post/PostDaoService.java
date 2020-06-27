package com.in28minutes.rest.webservices.restfulwebservices.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;

import org.springframework.stereotype.Component;

@Component
public class PostDaoService {

    private static List<Post> posts = new ArrayList<Post>();

    private static int postCount = 3;

    static {
        posts.add(new Post(1, "Hot dogs", new User(1, "User 1", new Date())));
        posts.add(new Post(2, "Pizza", new User(2, "User 2", new Date())));
        posts.add(new Post(3, "Temptation", new User(3, "User 3", new Date())));
    }

    public List<Post> getPosts(int userId) {
        return posts.stream()
            .filter(post -> post.getUser().getId().equals(userId))
            .collect(Collectors.toList());
    }

    public Post getPost(int userId, int postId) {
        return posts.stream()
            .filter(post -> post.getId().equals(postId) && post.getUser().getId().equals(userId))
            .findAny()
            .orElse(null);
    }

    public Post addPost(int userId, Post post) {
        if (null == post.getId()) {
            post.setId(++postCount);
        }
        posts.add(post);
        return post;
    }
}