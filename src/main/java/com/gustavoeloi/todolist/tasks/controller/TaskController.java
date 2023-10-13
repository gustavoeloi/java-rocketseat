package com.gustavoeloi.todolist.tasks.controller;

import com.gustavoeloi.todolist.tasks.model.TaskModel;
import com.gustavoeloi.todolist.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel){
        var task = this.taskRepository.save(taskModel);
        return task;
    }

}
