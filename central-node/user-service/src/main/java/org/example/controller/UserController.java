package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController
{

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showList() {
        return userService.listAll().toString();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user)
    {
        User savedUser = userService.create(user.getUsername(), user.getPassword(), user.getRole(), user.getEmail());
        if (savedUser == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User could not be created");
        }
        return ResponseEntity.ok("User successfully registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user)
    {
        User authenticatedUser = userService.login(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok("Login erfolgreich");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(user.getUsername()
                                         + "." + user.getPassword()  + " could not be authenticated");
        }
    }
}
