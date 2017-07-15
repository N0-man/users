package com.tradeaway.users;

import com.tradeaway.users.domain.User;
import com.tradeaway.users.repository.UserRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noumanm on 7/15/17.
 */

@Controller
@EnableAutoConfiguration
public class UserController {

    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello SpringBoot";
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAll() {
        final List<User> resultList = new ArrayList<User>();
        final Iterable<User> allUsers = userRepository.findAll();
        allUsers.forEach(resultList::add);
        return ResponseEntity.ok().body(resultList);
    }

    @PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create (@RequestBody User user) {
        final User createUser = userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/findUser/{lname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findByLastName (@PathVariable String lname) {
        final List<User> response = new ArrayList<>();
        final Iterable<User> searchResult = userRepository.findByLname(lname);
        searchResult.forEach(response::add);
        return ResponseEntity.ok().body(response);
    }
}
