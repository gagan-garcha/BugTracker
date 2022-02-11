package com.app.bugtracker.controller;

import com.app.bugtracker.exceptions.ResourceNotFoundException;
import com.app.bugtracker.model.User;
import com.app.bugtracker.model.dto.UserDTO;
import com.app.bugtracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/users";

    private final UserService mUserService;

    public UserController(UserService mUserService) {
        this.mUserService = mUserService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return mUserService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById (@PathVariable Long id){
        return mUserService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid User id"));
    }

    @GetMapping("/username")
    public User getUserByUsername (@RequestParam String username){
        return mUserService.findUserByUsername(username).orElseThrow(()-> new ResourceNotFoundException("Invalid User"));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody @Valid @NotNull UserDTO userDTO) {

        User user = User.builder().username(userDTO.getUsername()).photoUrl(userDTO.getPhotoUrl()).build();
        return mUserService.saveUser(user);
    }
}
