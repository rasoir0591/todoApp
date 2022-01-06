package com.example.todoApp.adapter;

import com.example.todoApp.model.Project;
import com.example.todoApp.model.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface SqlProjectRepository extends ProjectRepository, JpaRepository<Project, Integer> {

    @Override
    @Query("select distinct p from Project p join fetch p.groups")
    List<Project> findAll();

}