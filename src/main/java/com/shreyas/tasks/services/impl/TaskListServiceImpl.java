package com.shreyas.tasks.services.impl;

import com.shreyas.tasks.domain.dtos.TaskListDto;
import com.shreyas.tasks.domain.entities.TaskList;
import com.shreyas.tasks.mappers.TaskListMapper;
import com.shreyas.tasks.repositories.TaskListRepository;
import com.shreyas.tasks.services.TaskListService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

  @Override
  public TaskListDto createTaskList(TaskListDto taskListDto) {
    if(taskListDto.id() != null) {
      throw new IllegalArgumentException("Task list already exists");
    }
    if(taskListDto.title() == null || taskListDto.title().isBlank()) {
      throw new IllegalArgumentException("Task list title cannot be empty");
    }
    TaskList taskList = taskListMapper.fromDto(taskListDto);
    taskListRepository.save(taskList);
    return taskListMapper.toDto(taskList);
  }

  @Override
  public Optional<TaskListDto> getTaskListById(UUID id) {
    return taskListRepository.findById(id).map(taskListMapper::toDto);
  }

  @Override
  public TaskListDto updateTaskList(UUID id, TaskListDto taskListDto) {
    if(taskListDto.id() == null) {
      throw new IllegalArgumentException("Task list id cannot be empty");
    }
    if(!id.equals(taskListDto.id())) {
      throw new IllegalArgumentException("Task list id does not match");
    }
    if(taskListDto.title() == null || taskListDto.title().isBlank()) {
      throw new IllegalArgumentException("Task list title cannot be empty");
    }
    Optional<TaskList> taskList = taskListRepository.findById(id);
    TaskList updatedTaskList = taskListRepository.save(taskListMapper.fromDto(taskListDto));
    return taskListMapper.toDto(updatedTaskList);
  }

  @Override
  public void deleteTaskListById(UUID id) {
    taskListRepository.deleteById(id);
  }
}
