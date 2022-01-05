package com.example.todoApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "project_steps")
public class ProjectSteps {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Project steps desc cannot be empty")
    private  String description;
    private int daysToDeadline;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDaysToDeadline() {
        return daysToDeadline;
    }

    public void setDaysToDeadline(int daysToDeadline) {
        this.daysToDeadline = daysToDeadline;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}