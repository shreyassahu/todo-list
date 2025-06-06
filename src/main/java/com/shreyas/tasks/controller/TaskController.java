package com.shreyas.tasks.controller;

import com.shreyas.tasks.domain.dtos.TaskDto;
import com.shreyas.tasks.services.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists/{task_list_id}/tasks")
@CrossOrigin(origins = "http://localhost:5174")
public class TaskController {
  private final TaskService taskService;
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable UUID task_list_id) {
    List<TaskDto> tasks = taskService.listTasks(task_list_id);
    return ResponseEntity.ok(tasks);
  }

  @PostMapping
  public ResponseEntity<TaskDto> createTask(@PathVariable UUID task_list_id, @RequestBody TaskDto taskDto) {
    return ResponseEntity.ok(taskService.createTask(task_list_id, taskDto));
  }

  @GetMapping("/{task_id}")
  public ResponseEntity<TaskDto> getTask(@PathVariable UUID task_list_id, @PathVariable UUID task_id) {
    Optional<TaskDto> taskDtoOptional = taskService.getTask(task_list_id, task_id);
    return taskDtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{task_id}")
  public ResponseEntity<TaskDto> updateTask(@PathVariable UUID task_list_id, @PathVariable UUID task_id, @RequestBody TaskDto taskDto) {
    return ResponseEntity.ok(taskService.updateTask(task_list_id, task_id, taskDto));
  }

  @DeleteMapping("/{task_id}")
  public ResponseEntity<Void> deleteTaskList(@PathVariable UUID task_list_id, @PathVariable UUID task_id) {
    if(taskService.getTask(task_list_id, task_id).isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    taskService.deleteTask(task_list_id, task_id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
