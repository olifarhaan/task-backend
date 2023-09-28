package com.task.taskBackend.controller;

import com.task.taskBackend.exception.ResourceNotFoundException;
import com.task.taskBackend.model.Task;
import com.task.taskBackend.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = {"https://localhost:3000/"})
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    private List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @PostMapping
    private Task addTask(@RequestBody Task task){
        taskRepository.save(task);
        return task;
    }

    @GetMapping("{id}")
    private ResponseEntity<Task> getTaskById(@PathVariable long id){
        Task task= taskRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Task not exist with id "+id)
        );
        return ResponseEntity.ok(task);
    }
    @PutMapping("{id}")
    private ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task updatedTask){
        Task originalTask= taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found with id "+id));
        originalTask.setTitle(updatedTask.getTitle());
        originalTask.setDescription(updatedTask.getDescription());
        taskRepository.save(originalTask);
        return ResponseEntity.ok(originalTask);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Task> deleteTask(@PathVariable long id){
        Task task= taskRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task doesn't exist with id "+id));
        taskRepository.delete(task);
        return  ResponseEntity.ok(task);
    }
}
