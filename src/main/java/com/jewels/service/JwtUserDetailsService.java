package com.jewels.service;

import com.jewels.model.User;
import com.jewels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Injecting UserRepository to interact with the database

    // Overriding the method to load user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve the user from the repository by username
        User user = userRepository.findByUsername(username);
        // If user is not found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        // Return a UserDetails object containing the user's information
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
