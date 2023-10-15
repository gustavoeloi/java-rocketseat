package com.gustavoeloi.todolist.tasks.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "TB_TASKS")
public class TaskModel {
    @Id
    @GeneratedValue(generator = "UUID")
    UUID id;
    String description;
    @Column(unique = true, length = 50)
    String title;
    LocalDateTime startAt;
    LocalDateTime endAt;
    String priority;
    UUID userId;
    @CreationTimestamp
    LocalDateTime createdAt;

    public void setTitle(String title) throws Exception{
        if(title.length() >= 50){
            throw new Exception("O máximo de caracteres permitidos é 50");
        }

        this.title = title;


    }
}
