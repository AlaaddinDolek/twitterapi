package com.twitterapi.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twitterapi.exceptions.EmailAlreadyExistsException;
import com.twitterapi.exceptions.UsernameAlreadyExistsException;
import com.twitterapi.exceptions.UsernameNotFoundException;
import com.twitterapi.persistence.model.UserDto;
import com.twitterapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Long createUser(@Valid @RequestParam String username, String password, String email)
            throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        return userService.createUser(username, password, email);
    }

    @DeleteMapping
    public void deleteUserById(@RequestParam Long id) throws UsernameNotFoundException {
        userService.deleteUserById(id);
    }

    @GetMapping("/info/by-username")
    public UserDto findUserByUsername(@RequestParam String username) throws UsernameNotFoundException {
        return userService.findUserByUsername(username);
    }

    @GetMapping("/info/by-email")
    public UserDto findUserByEmail(@RequestParam String email) throws UsernameNotFoundException {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/info/{id}")
    public UserDto findUserById(@PathVariable("id") Long id) throws UsernameNotFoundException {
       return userService.findUserById(id);
    }

    @PutMapping("/info/{id}")
    public UserDto updateUser(@PathVariable("id")Long id, @RequestParam String username)throws UsernameNotFoundException{
        return userService.updateUserById(id, username);
    }

}
