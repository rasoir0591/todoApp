package com.example.todoApp.model;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {
    List<TaskGroup> findAll();

    Optional<TaskGroup> findById(java.lang.Integer id);

    TaskGroup save(TaskGroup entity);

    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);
}
