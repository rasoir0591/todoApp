package com.example.todoApp.model;

import java.util.List;
import java.util.Optional;

public interface ProjectStepsRepository {
  List<ProjectSteps> findAll();

   Optional<ProjectSteps> findById(java.lang.Integer id);

  ProjectSteps save(ProjectSteps entity);
}