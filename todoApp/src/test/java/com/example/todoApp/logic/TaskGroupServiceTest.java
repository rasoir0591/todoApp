package com.example.todoApp.logic;

import com.example.todoApp.model.TaskGroup;
import com.example.todoApp.model.TaskGroupRepository;
import com.example.todoApp.model.TaskRepository;
import com.example.todoApp.model.projection.GroupReadModel;
import com.example.todoApp.model.projection.GroupWriteModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskGroupServiceTest {
    @Test
    @DisplayName("Should throw exception if group have undone tasks")
    void toggleTaskGroup_undoneTasks_throwsIllegalStateException(){
        //given
        TaskGroupRepository mockGroupRepository = mock(TaskGroupRepository.class);
        //and
        TaskRepository mockTaskRepository = mock(TaskRepository.class);
        //and
        when(mockTaskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(true);
        //system under test
        var toTest = new TaskGroupService(mockGroupRepository, mockTaskRepository);
        //when
        var exception = catchThrowable(()-> toTest.toggleGroup(0));
        //then
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("has undone tasks");
    }
    @Test
    @DisplayName("Should throw exception if no tasks for given")
    void toggleTaskGroup_noTask_throwsIllegalStateException(){
        //given
        TaskGroupRepository mockGroupRepository = mock(TaskGroupRepository.class);
        //and
        TaskRepository mockTaskRepository = mock(TaskRepository.class);
        //and
        when(mockTaskRepository.findById(anyInt())).thenReturn(Optional.empty());
        //system under test
        var toTest = new TaskGroupService(mockGroupRepository, mockTaskRepository);
        //when
        var exception = catchThrowable(()-> toTest.toggleGroup(0));
        //then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("");
    }
    @Test
    @DisplayName("Should toggle state")
    void createTaskGroup_allOk_toggleAndSave(){
        var source = mock(GroupWriteModel.class);
        //given
        TaskGroupRepository mockGroupRepository = mock(TaskGroupRepository.class);
        //and
        TaskRepository mockTaskRepository = mock(TaskRepository.class);
        //system under test
        var toTest = new TaskGroupService(mockGroupRepository, mockTaskRepository);
        //then


    }

}