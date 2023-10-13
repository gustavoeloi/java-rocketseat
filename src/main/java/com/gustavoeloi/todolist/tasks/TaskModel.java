package com.gustavoeloi.todolist.tasks;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

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
}
