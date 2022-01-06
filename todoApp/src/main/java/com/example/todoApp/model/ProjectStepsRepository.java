package com.example.todoApp.model;

import java.util.List;
import java.util.Optional;

public interface ProjectStepsRepository {
  List<ProjectStep> findAll();

  Optional<ProjectStep> findById(java.lang.Integer id);

  ProjectStep save(ProjectStep entity);
}