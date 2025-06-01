package com.shreyas.tasks.mappers.impl;

import com.shreyas.tasks.domain.dtos.TaskDto;
import com.shreyas.tasks.domain.entities.Task;
import com.shreyas.tasks.mappers.TaskMapper;

import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
  @Override
  public Task fromDto(TaskDto taskDto) {
    return Task.builder()
            .id(taskDto.id())
            .title(taskDto.title())
            .description(taskDto.description())
            .dueDate(taskDto.dueDate())
            .priority(taskDto.priority())
            .status(taskDto.status())
            .build();
  }

  @Override
  public TaskDto toDto(Task task) {
    return new TaskDto(task.getId(), task.getTitle(), task.getDescription(),
            task.getDueDate(), task.getPriority(), task.getStatus());
  }
}
