package com.shreyas.tasks.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task_lists")
public class TaskList {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "created", nullable = false)
  private LocalDateTime created;

  @Column(name = "updated", nullable = false)
  private LocalDateTime modified;

  @OneToMany(mappedBy = "taskList", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
  private List<Task> tasks;
}
