package com.shreyas.tasks.mappers.impl;

import com.shreyas.tasks.domain.dtos.TaskListDto;
import com.shreyas.tasks.domain.entities.Task;
import com.shreyas.tasks.domain.entities.TaskList;
import com.shreyas.tasks.domain.entities.TaskStatus;
import com.shreyas.tasks.mappers.TaskListMapper;
import com.shreyas.tasks.mappers.TaskMapper;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Component
public class TaskListMapperImpl implements TaskListMapper {

  private final TaskMapper taskMapper;

  public TaskListMapperImpl(TaskMapper taskMapper) {
    this.taskMapper = taskMapper;
  }

  @Override
  public TaskList fromDto(TaskListDto taskListDto) {
    return TaskList.builder()
            .id(taskListDto.id())
            .title(taskListDto.title())
            .description(taskListDto.description())
            .tasks(Optional.ofNullable(taskListDto.tasks())
                    .map(tasks -> tasks.stream()
                            .map(taskMapper::fromDto).toList())
                    .orElse(null)).build();
  }

  @Override
  public TaskListDto toDto(TaskList taskList) {
    return new TaskListDto(
            taskList.getId(),
            taskList.getTitle(),
            taskList.getDescription(),
            Optional.ofNullable(taskList.getTasks())
                    .map(Collection::size).orElse(0)
            , calculateTaskListProgress(taskList.getTasks())
            , Optional.ofNullable(taskList.getTasks())
            .map(tasks -> tasks.stream()
                    .map(taskMapper::toDto).toList())
            .orElse(null));
  }

  private Double calculateTaskListProgress(List<Task> tasks) {
    if(tasks == null) {
      return null;
    }
    double numOfClosedTasks = tasks.stream().filter(task -> task.getStatus() == TaskStatus.CLOSED).count();

    return numOfClosedTasks / tasks.size();
  }
}
