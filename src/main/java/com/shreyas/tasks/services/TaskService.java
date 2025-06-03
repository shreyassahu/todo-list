package com.shreyas.tasks.services;

import com.shreyas.tasks.domain.dtos.TaskDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
  List<TaskDto> listTasks(UUID taskListId);
  TaskDto createTask(UUID taskListId, TaskDto taskDto);
  Optional<TaskDto> getTask(UUID taskListId, UUID taskId);
  TaskDto updateTask(UUID taskListId, UUID taskId, TaskDto taskDto);
  void deleteTask(UUID taskListId, UUID taskId);
}
