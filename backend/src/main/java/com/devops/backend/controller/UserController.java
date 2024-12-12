package com.devops.backend.controller;

import com.devops.backend.form.LogInForm;
import com.devops.backend.form.UserForm;
import com.devops.backend.model.Address;
import com.devops.backend.model.CustomType.UserType;
import com.devops.backend.model.User;
import com.devops.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/user", produces = "application/json")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        }

    @Tag(name="Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return  ResponseEntity.ok(users);
    }

    @Tag(name="Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/clients")
    public ResponseEntity<List<User>> getAllClients() {
        List<User> users = userService.getAllClients();
        return  ResponseEntity.ok(users);
    }

    @Tags({
            @Tag(name = "Company GET Endpoints / Admin GET Endpoints"),
            @Tag(name = "User GET Endpoints"),
    })
    @Operation(summary = "Get a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved"),
            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @Tag(name = "User POST Endpoints")
    @Operation(summary = "Create/sign up a new user")
    @PostMapping(value="/signup",consumes = "application/json")
    public ResponseEntity<User> createUser(@RequestBody UserForm userForm) {
        // TODO: Add validation for userForm
        if (!userForm.getUserType().equals(UserType.CLIENT) && !userForm.getUserType().equals(UserType.BUSINESS)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User savedUser = userService.saveUser(userForm);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Tags({
            @Tag(name = "Company POST Endpoints / Admin POST Endpoints"),
            @Tag(name = "User POST Endpoints"),
            @Tag(name = "Driver POST Endpoints")
    })
    @Operation(summary = "login a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping(value="/login",consumes = "application/json")
    public ResponseEntity<User> logInUser(@RequestBody LogInForm logInForm) {
        User user = userService.logInUser(logInForm);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(user);
    }

    @Tag(name = "Company PUT Endpoints / Admin PUT Endpoints")
    @Operation(summary = "Activate a user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User account activated"),
            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value="/{userId}/activate")
    public ResponseEntity<User> activateUser(@PathVariable Long userId) {
        User activatedUser = userService.activateUser(userId);
        return ResponseEntity.ok(activatedUser);
    }

    @Tag(name = "User PUT Endpoints")
    @Operation(summary = "Update a user's profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User profile updated"),
            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value="/{userId}",consumes = "application/json")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long userId, @RequestBody User user) {
            User updatedUser = userService.updateUser(userId, user);
            return ResponseEntity.ok(updatedUser);
    }

//    @Tag(name = "User PUT Endpoints")
//    @Operation(summary = "Update a user's first name")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User first name updated"),
//            @ApiResponse(responseCode = "400", description = "Invalid first name", content = @Content),
//            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
//    @PutMapping(value="/{userId}/firstName",consumes = "application/json")
//    public ResponseEntity<User> updateUserFirstName(@PathVariable Long userId, @RequestBody String firstName) {
//        if (ControllerValidator.isValidString(firstName)) {
//            User updatedUser = userService.updateUserFirstName(userId, firstName);
//            return ResponseEntity.ok(updatedUser);
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @Tag(name = "User PUT Endpoints")
//    @Operation(summary = "Update a user's last name")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User last name updated"),
//            @ApiResponse(responseCode = "400", description = "Invalid last name", content = @Content),
//            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
//    @PutMapping(value="/{userId}/lastName",consumes = "application/json")
//    public ResponseEntity<User> updateUserLastName(@PathVariable Long userId, @RequestBody String lastName) {
//        if (ControllerValidator.isValidString(lastName)) {
//            User updatedUser = userService.updateUserLastName(userId, lastName);
//            return ResponseEntity.ok(updatedUser);
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @Tag(name = "User PUT Endpoints")
//    @Operation(summary = "Update a user's address")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User address updated"),
//            @ApiResponse(responseCode = "400", description = "Invalid address", content = @Content),
//            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
//    @PutMapping(value="/{userId}/address", consumes = "application/json")
//    public ResponseEntity<User> updateUserAddress(@PathVariable Long userId, @RequestBody Address address) {
//        if (ControllerValidator.isValidAddress(address)) {
//            User updatedUser = userService.updateUserAddress(userId, address);
//            return ResponseEntity.ok(updatedUser);
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @Tag(name = "User PUT Endpoints")
//    @Operation(summary = "Update a user's phone number")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User phone number updated"),
//            @ApiResponse(responseCode = "400", description = "Invalid phone number", content = @Content),
//            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
//    @PutMapping(value="/{userId}/phoneNumber",consumes = "application/json")
//    public ResponseEntity<User> updateUserPhoneNumber(@PathVariable Long userId, @RequestBody long phoneNumber) {
//        if (ControllerValidator.isValidPhoneNumber(phoneNumber)) {
//            User updatedUser = userService.updateUserPhoneNumber(userId, phoneNumber);
//            return ResponseEntity.ok(updatedUser);
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @Tag(name = "User PUT Endpoints")
//    @Operation(summary = "Update a user's email")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User email updated"),
//            @ApiResponse(responseCode = "400", description = "Invalid email", content = @Content),
//            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
//    @PutMapping(value="/{userId}/email",consumes = "application/json")
//    public ResponseEntity<User> updateUserEmail(@PathVariable Long userId, @RequestBody String email) {
//        if (ControllerValidator.isValidEmail(email)) {
//            User updatedUser = userService.updateUserEmail(userId, email);
//            return ResponseEntity.ok(updatedUser);
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @Tag(name = "User PUT Endpoints")
//    @Operation(summary = "Update a user's password")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User password updated"),
//            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
//    @PutMapping(value="/{userId}/password",consumes = "application/json")
//    public ResponseEntity<User> updateUserPassword(@PathVariable Long userId, @RequestBody String password) {
//        User updatedUser = userService.updateUserPassword(userId, password);
//        return ResponseEntity.ok(updatedUser);
//    }

    @Tags({
            @Tag(name = "Company DELETE Endpoints / Admin DELETE Endpoints"),
            @Tag(name = "User DELETE Endpoints")
    })
    @Operation(summary = "Delete (deactivate) a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deactivated"),
            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping(value="/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
        User user = userService.deactivateUser(userId);
        return ResponseEntity.ok(user);
    }
}
