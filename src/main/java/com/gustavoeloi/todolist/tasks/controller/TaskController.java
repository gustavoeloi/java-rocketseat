package com.gustavoeloi.todolist.tasks.controller;

import com.gustavoeloi.todolist.tasks.model.TaskModel;
import com.gustavoeloi.todolist.tasks.repository.TaskRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var requestUserId = request.getAttribute("userId");

        taskModel.setUserId((UUID) requestUserId);
        var task = this.taskRepository.save(taskModel);
        return task;
    }

}
