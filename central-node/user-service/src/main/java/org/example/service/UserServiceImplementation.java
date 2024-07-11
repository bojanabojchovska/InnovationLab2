package org.example.service;

import org.example.model.Role;
import org.example.model.User;
import org.example.model.execptions.InvalidArgumentsException;
import org.example.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> listAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User create(String username, String password, Role role, String email) {
        User newUser = new User(username, passwordEncoder.encode(password), role, email);
        return userRepository.save(newUser);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                Collections.singletonList(user.getRole())
        );
    }

    @Override
    public User login(String username, String password)
    {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new InvalidArgumentsException("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword()))
        {
            throw new InvalidArgumentsException("Password is incorrect");
        }

        return user;
    }

}
