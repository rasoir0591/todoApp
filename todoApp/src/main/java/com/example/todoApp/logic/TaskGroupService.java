package com.example.todoApp.logic;

import com.example.todoApp.model.TaskGroup;
import com.example.todoApp.model.TaskGroupRepository;
import com.example.todoApp.model.TaskRepository;
import com.example.todoApp.model.projection.GroupReadModel;
import com.example.todoApp.model.projection.GroupWriteModel;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequestScope
public class TaskGroupService {
    private TaskGroupRepository repository;
    private TaskRepository taskRepository;

    public TaskGroupService(TaskGroupRepository repository, TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }

    public GroupReadModel createGroup(final GroupWriteModel source){
        TaskGroup result = repository.save(source.toGroup());
        return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll(){
        return repository.findAll()
                .stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public  void toggleGroup(int groupId){
        if (taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)){
            throw new IllegalStateException("Group has undone tasks. Done all task first");
        }
        TaskGroup result = repository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        result.setDone(!result.isDone());
    }
}
