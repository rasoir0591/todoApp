package com.example.todoApp.controller;

import com.example.todoApp.model.Task;
import com.example.todoApp.model.TaskRepository;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class TaskController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository;

    TaskController(final TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/tasks", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Task>> readAllTasks(){
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/tasks")
    ResponseEntity<List<Task>> readAllTasks(Pageable page){
        logger.info("Custom pageable!");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }
    @PutMapping("/tasks/{id}")
    ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody @Valid Task toUpdate){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(task -> {
                    task.updateFrom(toUpdate);
                    repository.save(task);
                });
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/tasks")
    ResponseEntity<Task> createTask(@RequestBody @Valid Task toCreate){
        Task result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> readTask(@PathVariable int id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Transactional
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<?> toggleTask(@PathVariable int id){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                        .ifPresent(task -> task.setDone(!task.isDone()));

        return ResponseEntity.noContent().build();
    }
}
//package com.example.todoApp.controller;
//
//import com.example.todoApp.model.Task;
//import com.example.todoApp.model.TaskRepository;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.net.URI;
//import java.util.List;
//
//@Controller
//public class TaskController {
//    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TaskController.class);
//    private final TaskRepository repository;
//
//    TaskController(final TaskRepository repository) {
//        this.repository = repository;
//    }
//
//    @RequestMapping(value = "/tasks", params = {"!sort", "!page", "!size"}, method = RequestMethod.GET)
//    ResponseEntity<List<Task>> readAllTasks(){
//        logger.warn("Exposing all the tasks!");
//        return ResponseEntity.ok(repository.findAll());
//    }
//
//    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
//    ResponseEntity<List<Task>> readAllTasks(Pageable page){
//        logger.info("Custom pageable!");
//        return ResponseEntity.ok(repository.findAll(page).getContent());
//    }
//    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
//    ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody @Valid Task toUpdate){
//        if(!repository.existsById(id)){
//            return ResponseEntity.notFound().build();
//        }
//        toUpdate.setId(id);
//        repository.save(toUpdate);
//        return ResponseEntity.noContent().build();
//    }
//    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
//    ResponseEntity<Task> createTask(@RequestBody @Valid Task toCreate){
//        Task result = repository.save(toCreate);
//        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
//    }
//    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
//    ResponseEntity<Task> readTask(@PathVariable int id){
//        return repository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//}
