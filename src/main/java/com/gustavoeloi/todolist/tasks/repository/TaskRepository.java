package com.gustavoeloi.todolist.tasks.repository;

import com.gustavoeloi.todolist.tasks.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
}
