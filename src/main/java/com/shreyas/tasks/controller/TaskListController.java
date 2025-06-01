package com.shreyas.tasks.controller;

import com.shreyas.tasks.domain.dtos.TaskListDto;
import com.shreyas.tasks.services.TaskListService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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
}
