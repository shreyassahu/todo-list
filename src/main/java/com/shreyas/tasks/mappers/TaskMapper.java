package com.shreyas.tasks.mappers;

import com.shreyas.tasks.domain.dtos.TaskDto;
import com.shreyas.tasks.domain.entities.Task;

public interface TaskMapper {
  Task fromDto(TaskDto dto);
  TaskDto toDto(Task task);
}
