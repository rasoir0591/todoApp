package com.example.todoApp.adapter;

import com.example.todoApp.model.TaskGroup;
import com.example.todoApp.model.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Integer> {
    @Query("from TaskGroup g join fetch g.tasks")
    java.util.List<com.example.todoApp.model.TaskGroup> findAll();

    boolean existsByDoneIsFalseAndProject_Id(java.lang.Integer projectId);
}
