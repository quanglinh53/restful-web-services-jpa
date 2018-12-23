package com.quanglinhit.restfulwebservices;

import com.quanglinhit.dao.UserDaoService;
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

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service = new UserDaoService();

    @GetMapping(path = "/users")
    public List<User> showAllUsers(){
        return service.showAll();
    }

    @GetMapping(path = "/users/{id}")
    public Resource<User> findUser(@PathVariable int id){
        User newUser = service.findById(id);
        if (newUser == null) throw new UserNotFoundException("id - " + id);

        Resource<User> resource = new Resource<User>(newUser);
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(this.getClass()).showAllUsers()
        );
        resource.add(linkTo.withRel("all-users"));

        // HATEOAS
        return resource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid  @RequestBody User user){
        User saveUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build(); // 201 Created
    }

    @DeleteMapping(path = "/users/{id}")
    public void removeUser(@PathVariable int id){
        User saveUser = service.deleteById(id);
        if (saveUser == null) throw new UserNotFoundException("id - " + id);

    }

}
