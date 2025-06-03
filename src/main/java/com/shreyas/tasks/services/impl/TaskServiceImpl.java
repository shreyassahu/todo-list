package com.shreyas.tasks.services.impl;

import com.shreyas.tasks.domain.dtos.TaskDto;
import com.shreyas.tasks.domain.entities.Task;
import com.shreyas.tasks.domain.entities.TaskList;
import com.shreyas.tasks.domain.entities.TaskPriority;
import com.shreyas.tasks.domain.entities.TaskStatus;
import com.shreyas.tasks.mappers.TaskMapper;
import com.shreyas.tasks.repositories.TaskListRepository;
import com.shreyas.tasks.repositories.TaskRepository;
import com.shreyas.tasks.services.TaskService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final TaskListRepository taskListRepository;
  private final TaskMapper taskMapper;

  public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskListRepository = taskListRepository;
    this.taskMapper = taskMapper;
  }
  @Override
  public List<TaskDto> listTasks(UUID taskListId) {
    List<Task> tasks = taskRepository.findByTaskListId(taskListId);
    return tasks.stream().map(taskMapper::toDto).toList();
  }

  @Override
  public TaskDto createTask(UUID taskListId, TaskDto taskDto) {
    if(taskDto.id() != null) {
      throw new IllegalArgumentException("Task already has an ID");
    }
    if(taskDto.title() == null || taskDto.title().isBlank()) {
      throw new IllegalArgumentException("Task title cannot be empty");
    }
    TaskStatus status = TaskStatus.OPEN;
    TaskPriority priority = Optional.ofNullable(taskDto.priority()).orElse(TaskPriority.MEDIUM);
    TaskList taskList = taskListRepository.findById(taskListId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid task list ID"));
    Task task = taskMapper.fromDto(taskDto);
    task.setStatus(status);
    task.setPriority(priority);
    task.setTaskList(taskList);
    taskRepository.save(task);
    return taskMapper.toDto(task);
  }

  @Override
  public Optional<TaskDto> getTask(UUID taskListId, UUID taskId) {
    return taskRepository.findByTaskListIdAndId(taskListId, taskId).map(taskMapper::toDto);
  }

  @Override
  public TaskDto updateTask(UUID taskListId, UUID taskId, TaskDto taskDto) {
    return null;
  }

  @Override
  public void deleteTask(UUID taskListId, UUID taskId) {

  }
}
