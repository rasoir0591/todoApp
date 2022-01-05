package com.example.todoApp.adapter;

import com.example.todoApp.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

interface SqlProjectRepository extends com.example.todoApp.model.ProjectRepository, JpaRepository<Project, Integer> {
    @org.springframework.data.jpa.repository.Query("from Project p join fetch p.taskGroups")
    java.util.List<com.example.todoApp.model.Project> findAll();
}