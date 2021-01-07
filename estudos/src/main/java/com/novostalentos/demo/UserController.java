package com.novostalentos.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crud")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/save")
    public void save(@RequestBody User user){
        userRepository.save(user);
    }

    @PostMapping("/find/id")

    @PostMapping("/find/name")

    @PostMapping("/find-all")

    @PostMapping("/update/idade")

    @PostMapping("/update/email")

    @PostMapping("/delete/user")
}
