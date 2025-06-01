package com.shreyas.tasks.services.impl;

import com.shreyas.tasks.domain.dtos.TaskListDto;
import com.shreyas.tasks.mappers.TaskListMapper;
import com.shreyas.tasks.repositories.TaskListRepository;
import com.shreyas.tasks.services.TaskListService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

  private final TaskListRepository taskListRepository;
  private final TaskListMapper taskListMapper;

  public TaskListServiceImpl(TaskListRepository taskListRepository, TaskListMapper taskListMapper) {
    this.taskListRepository = taskListRepository;
    this.taskListMapper = taskListMapper;
  }

  @Override
  public List<TaskListDto> listTaskLists() {
    return taskListRepository.findAll().stream().map(taskListMapper::toDto).toList();
  }
}
