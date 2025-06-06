package com.shreyas.tasks.domain.entities;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "due_date")
  private LocalDateTime dueDate;

  @Column(name = "status", nullable = false)
  private TaskStatus status;

  @Column(name = "priority", nullable = false)
  private TaskPriority priority;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "task_list_id")
  private TaskList taskList;

  @CreationTimestamp
  @Column(name = "created", nullable = false)
  private LocalDateTime created;

  @UpdateTimestamp
  @Column(name = "updated", nullable = false)
  private LocalDateTime modified;

}
