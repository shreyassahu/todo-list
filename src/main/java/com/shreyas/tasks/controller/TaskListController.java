package com.shreyas.tasks.controller;

import com.shreyas.tasks.domain.dtos.TaskListDto;
import com.shreyas.tasks.services.TaskListService;

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
@RequestMapping(path = "/api/task-lists")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskListController {

  TaskListService taskListService;
  public TaskListController(TaskListService taskListService) {
    this.taskListService = taskListService;
  }

  @GetMapping
  public List<TaskListDto> listTaskLists() {
    return taskListService.listTaskLists();
  }

  @PostMapping
  public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
    return taskListService.createTaskList(taskListDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskListDto> getTaskListById(@PathVariable UUID id) {
    Optional<TaskListDto> taskListDtoOptional = taskListService.getTaskListById(id);
    return taskListDtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskListDto> updateTaskList(@PathVariable UUID id, @RequestBody TaskListDto taskListDto) {
    return new ResponseEntity<>(taskListService.updateTaskList(id, taskListDto), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTaskList(@PathVariable UUID id) {
    if(taskListService.getTaskListById(id).isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    taskListService.deleteTaskListById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
