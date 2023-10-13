package com.gustavoeloi.todolist.tasks.controller;

import com.gustavoeloi.todolist.tasks.model.TaskModel;
import com.gustavoeloi.todolist.tasks.repository.TaskRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var requestUserId = request.getAttribute("userId");
        taskModel.setUserId((UUID) requestUserId);

        LocalDateTime currentDate = LocalDateTime.now();
        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início / término da tarefa não pode ser menor que a data atual");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de ínicio deve ser antes da data de término.");
        }

        if(taskModel.getEndAt().isBefore(taskModel.getStartAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de término deve ser depois da data de ínicio.");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskModel>> list(HttpServletRequest request){
        var requestUserId = request.getAttribute("userId");
        var tasks = this.taskRepository.findByUserId((UUID) requestUserId);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PutMapping("/{id}")
    public TaskModel update(@PathVariable UUID id, @RequestBody TaskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute("userId");
        taskModel.setUserId((UUID) idUser);
        taskModel.setId(id);
        return this.taskRepository.save(taskModel);
    }


}
