package com.shreyas.tasks.services;

import com.shreyas.tasks.domain.dtos.TaskListDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
  List<TaskListDto> listTaskLists();
  TaskListDto createTaskList(TaskListDto taskListDto);
  Optional<TaskListDto> getTaskListById(UUID id);
}
