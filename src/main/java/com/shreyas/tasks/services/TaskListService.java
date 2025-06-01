package com.shreyas.tasks.services;

import com.shreyas.tasks.domain.dtos.TaskListDto;
import java.util.List;

public interface TaskListService {
  List<TaskListDto> listTaskLists();
}
