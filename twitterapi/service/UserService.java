package com.twitterapi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.twitterapi.exceptions.EmailAlreadyExistsException;
import com.twitterapi.exceptions.UsernameAlreadyExistsException;
import com.twitterapi.exceptions.UsernameNotFoundException;
import com.twitterapi.persistence.model.User;
import com.twitterapi.persistence.model.UserDto;
import com.twitterapi.persistence.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
 

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
      
    }

    public Long createUser(String username, String password, String email)
            throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists !");
        }
        Optional<User> mail = userRepository.findUserByEmail(email);

        if (mail.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists !");
        }

        var newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        var savedUser = userRepository.save(newUser);
        return savedUser.getId();
    }

    public UserDto findUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        return user.get().toUserDto();
    }

    public UserDto findUserByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        return user.get().toUserDto();
    }

    public UserDto findUserById(Long id) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        return user.get().toUserDto();
    }

    public UserDto updateUserById(Long id, String newUsername) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        user.get().setUsername(newUsername);

        return user.get().toUserDto();
    }

    public void deleteUserById(Long id) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        userRepository.deleteById(id);
    }
}
