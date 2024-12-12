package com.devops.backend.service;

import com.devops.backend.form.LogInForm;
import com.devops.backend.form.UserForm;
import com.devops.backend.model.Address;
import com.devops.backend.model.CustomType.UserType;
import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.model.User;
import com.devops.backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initialize() {
        // check if there are any users of type admin
        long userCount = userRepository.countByUserType(UserType.ADMIN);
        if (userCount == 0) {
            createDefaultAdmin();
        }
    }

    private void createDefaultAdmin() {
        User adminUser = new User();
        adminUser.setFirst_name("Ali");
        adminUser.setLast_name("Habibi");
        adminUser.setEmail("admin@example.com");
        adminUser.setPassword("admin123");
        adminUser.setAddress(getDefaultAddress());
        adminUser.setPhone_number(1234567890);
        adminUser.setUserType(UserType.ADMIN);
        userRepository.save(adminUser);
    }

    private Address getDefaultAddress() {
        Address address = new Address();
        address.setNumber(1455);
        address.setStreet("Boulevard de Maisonneuve");
        address.setCity("Montreal");
        address.setCountry("Canada");
        address.setZipCode("H3G 1M8");
        return address;
    }

    public User saveUser(UserForm userForm) {
        // check if email is unique
        User existingUser = userRepository.findByEmail(userForm.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();
        user.setFirst_name(userForm.getFirst_name());
        user.setLast_name(userForm.getLast_name());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setUserType(userForm.getUserType());
        user.setAddress(getDefaultAddress());
        user.setPhone_number(1234567890);
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new IllegalArgumentException("User ID " + userId + " not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//     // Update a user's first name
//    public User updateUserFirstName(Long userId, String firstName) {
//        User existingUser = userRepository.findById(userId).orElse(null);
//        if (existingUser != null) {
//            existingUser.setFirst_name(firstName);
//            return userRepository.save(existingUser);
//        } else {
//            throw new IllegalArgumentException("User with id " + userId + " not found");
//        }
//    }
//
//    // Update a user's last name
//    public User updateUserLastName(Long userId, String lastName) {
//        User existingUser = userRepository.findById(userId).orElse(null);
//        if (existingUser != null) {
//            existingUser.setLast_name(lastName);
//            return userRepository.save(existingUser);
//        } else {
//            throw new IllegalArgumentException("User with id " + userId + " not found");
//        }
//    }
//
//    // Update a user's address
//    public User updateUserAddress(Long userId, Address address) {
//        User existingUser = userRepository.findById(userId).orElse(null);
//        if (existingUser != null) {
//            existingUser.setAddress(address);
//            return userRepository.save(existingUser);
//        } else {
//            throw new IllegalArgumentException("User with id " + userId + " not found");
//        }
//    }
//
//    // Update a user's phone number
//    public User updateUserPhoneNumber(Long userId, long phoneNumber) {
//        User existingUser = userRepository.findById(userId).orElse(null);
//        if (existingUser != null) {
//            existingUser.setPhone_number(phoneNumber);
//            return userRepository.save(existingUser);
//        } else {
//            throw new IllegalArgumentException("User with id " + userId + " not found");
//        }
//    }
//
//    // update a user's email
//    public User updateUserEmail(Long userId, String email) {
//        User existingUser = userRepository.findById(userId).orElse(null);
//        if (existingUser != null) {
//            existingUser.setEmail(email);
//            return userRepository.save(existingUser);
//        } else {
//            throw new IllegalArgumentException("User with id " + userId + " not found");
//        }
//    }
//
//    // update a user's password
//    public User updateUserPassword(Long userId, String password) {
//        User existingUser = userRepository.findById(userId).orElse(null);
//        if (existingUser != null) {
//            existingUser.setPassword(password);
//            return userRepository.save(existingUser);
//        } else {
//            throw new IllegalArgumentException("User with id " + userId + " not found");
//        }
//    }

    public User activateUser(Long id) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setActive(true);
            return userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
    }

    public User deactivateUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setActive(false);
            return userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }
    
    }


    public User logInUser(LogInForm logInForm) {
        User user = userRepository.findByEmail(logInForm.getEmail());
        if (user != null && user.getPassword().equals(logInForm.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public User updateUser(Long userId, User user) {
        // check if user exists
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setFirst_name(user.getFirst_name());
            existingUser.setLast_name(user.getLast_name());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setAddress(user.getAddress());
            existingUser.setPhone_number(user.getPhone_number());
            existingUser.setUserType(user.getUserType());
            return userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }
    }

    public List<User> getAllClients() {
        return userRepository.findAllByUserType(UserType.CLIENT);
    }
}
