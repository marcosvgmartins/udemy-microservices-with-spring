package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.in28minutes.rest.webservices.restfulwebservices.post.Post;
import com.in28minutes.rest.webservices.restfulwebservices.post.PostDaoService;
import com.in28minutes.rest.webservices.restfulwebservices.post.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {

    /**
     * Spring will inject the dependency
     */
    @Autowired
    private UserRepository userService;

    @Autowired
    private PostRepository postService;

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = findUser(id);

        // HATEOAS
        // Let's also indicate how a client could get all users
        EntityModel<User> model = new EntityModel<User>(user);

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        model.add(linkTo.withRel("all-users"));

        return model;
    }

    /**
     * javax.validation is also in the classpath because of Spring Boot Starter Web
     * @param user
     * @return
     */
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User created = userService.save(user);
        /**
         * Get the URI of the current request, add a variable called "id" that will be replaced by the new user's id
         * and send this URI back with a 201 Created response
         */
        URI newUserUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(newUserUri).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        Optional<User> user = userService.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException(id);
        }

        userService.delete(user.get());
    }

    // Posts

    @GetMapping("/jpa/users/{userId}/posts")
    public List<Post> getPostsForUser(@PathVariable int userId) {
        // First, find the user
        User user = findUser(userId);

        // Now, find the posts
        return user.getPosts();
    }

    @PostMapping("/jpa/users/{userId}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int userId, @RequestBody Post post) {
        User user = findUser(userId);
        post.setUser(user);
        post = postService.save(post);

        URI newPostUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{postId}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(newPostUri).build();
    }

    /*@GetMapping("/jpa/users/{userId}/posts/{postId}")
    public Post getPostForUser(@PathVariable int userId, @PathVariable int postId) {
        return postService.getPost(userId, postId);
    }*/

    private User findUser(int id) {
        Optional<User> user = userService.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException(id);
        }

        return user.get();
    }

}