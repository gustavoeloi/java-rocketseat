package com.gustavoeloi.todolist.controller;

import com.gustavoeloi.todolist.model.UserModel;
import com.gustavoeloi.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var userName = this.userRepository.findByUserName(userModel.getUserName());

        if (userName != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome de usuário já existe!");
        }

        var usuario = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
}
