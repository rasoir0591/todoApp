package com.example.todoApp.model;

import org.springframework.data.jpa.repository.JpaRepository;

interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Integer> {

}
