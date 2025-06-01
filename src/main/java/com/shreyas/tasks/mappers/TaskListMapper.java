package com.shreyas.tasks.mappers;

import com.shreyas.tasks.domain.dtos.TaskListDto;
import com.shreyas.tasks.domain.entities.TaskList;

public interface TaskListMapper {
  TaskList fromDto(TaskListDto dto);
  TaskListDto toDto(TaskList taskList);

}
