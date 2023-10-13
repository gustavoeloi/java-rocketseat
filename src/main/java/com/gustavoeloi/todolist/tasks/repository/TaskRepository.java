package com.gustavoeloi.todolist.tasks.repository;

import com.gustavoeloi.todolist.tasks.model.TaskModel;
import com.gustavoeloi.todolist.users.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByUserId(UUID userId);
}
