package com.gustavoeloi.todolist.users.repository;

import com.gustavoeloi.todolist.users.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUserName(String username);
}
