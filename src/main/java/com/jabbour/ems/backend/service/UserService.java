package com.jabbour.ems.backend.service;

import com.jabbour.ems.backend.entity.Employee;
import com.jabbour.ems.backend.entity.User;
import com.jabbour.ems.backend.repository.DepartmentRepository;
import com.jabbour.ems.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        //make sure employee is not null, return log message to console.
        if(user == null) {
            LOGGER.log(Level.SEVERE,
                    "User is null. is your form connected to the application?");
            return;
        }
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
