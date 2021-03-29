package com.api.rest.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.entity.User;
import com.api.rest.service.UserService;




@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/users")
    public List<User> findAll(){
        //retornará todos los usuarios
        return userService.findAll();
    }
	
	@GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId){
        User user = userService.findById(userId);

        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
        }
        //retornará al usuario con id pasado en la url
        return user;
    }
	
	@PostMapping("/users")
    public User addUser(@RequestBody User user) {
        user.setId_user(0);
        //Este metodo guardará al usuario enviado
        userService.save(user);
        return user;
    }
	
	@PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.save(user);
        //este metodo actualizará al usuario enviado
        return user;
    }
	
	@DeleteMapping("users/{userId}")
    public String deteteUser(@PathVariable int userId) {
        User user = userService.findById(userId);
        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
        }
        userService.deleteById(userId);
        //Esto método, recibira el id de un usuario por URL y se borrará de la bd.
        return "Deleted user id_user - "+userId;
    }
}
