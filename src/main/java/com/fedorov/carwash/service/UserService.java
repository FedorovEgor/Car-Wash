package com.fedorov.carwash.service;

import com.fedorov.carwash.model.User;
import com.fedorov.carwash.repository.RoleRepository;
import com.fedorov.carwash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("User with such name not found.");
        }

        return user;
    }

    public User getCurrentlyLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public User getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new EntityNotFoundException("User wasn't found in database.");
        }

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }

    public void insertUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername()).orElse(null);

        if (userFromDb != null) {
            throw new IllegalArgumentException("Already in database!");
        }


        user.setUsername(user.getUsername());
        user.setRole(roleRepository.findByName("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean updateUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            User userFromDb = userRepository.getById(user.getId());
            user.setUsername(userFromDb.getUsername());
            user.setPassword(userFromDb.getPassword());
            user.setRole(user.getRole());
            userRepository.save(user);
            return true;
        }

        return false;
    }

    public boolean deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
