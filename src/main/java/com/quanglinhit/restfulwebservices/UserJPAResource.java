package com.quanglinhit.restfulwebservices;

import com.quanglinhit.entity.Post;
import com.quanglinhit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> showAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public Resource<User> findUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) throw new UserNotFoundException("id - " + id);

        Resource<User> resource = new Resource<>(user.get());
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(this.getClass()).showAllUsers()
        );
        resource.add(linkTo.withRel("all-users"));

        // HATEOAS
        return resource;
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> createUser(@Valid  @RequestBody User user){
        User saveUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();
        return ResponseEntity.created(location).build(); // 201 Created
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> showAllUsers(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()){
            throw new UserNotFoundException("id - " + id);
        }

        return userOptional.get().getPosts();
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()){
            throw new UserNotFoundException("id - " + id);
        }

        User newUser = userOptional.get();
        post.setUser(newUser);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).build(); // 201 Created

    }

}
