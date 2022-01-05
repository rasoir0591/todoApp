package com.example.todoApp.adapter;

import com.example.todoApp.model.ProjectSteps;
import com.example.todoApp.model.ProjectStepsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface SqlProjectStepsRepository extends ProjectStepsRepository, JpaRepository<ProjectSteps, Integer> {
    @Query("from ProjectSteps ps join fetch ps.project")
    List<ProjectSteps> findAll();
}