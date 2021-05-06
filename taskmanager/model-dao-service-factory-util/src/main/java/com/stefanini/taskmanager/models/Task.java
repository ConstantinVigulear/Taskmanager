package com.stefanini.taskmanager.models;

public class Task {
    private String taskTitle;
    private String description;
    private int taskId;

    public Task(String taskTitle, String description, int taskId) {
        this.taskTitle = taskTitle;
        this.description = description;
        this.taskId = taskId;
    }
    
    public Task(String taskTitle, String description) {
        this.taskTitle = taskTitle;
        this.description = description;
    }
    
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setId(int taskId) {
    	this.taskId = taskId;
    }
    
    public int getId() {
    	return taskId;
    }

    @Override
    public String toString() {
        return String.format("%-15s", this.getTaskTitle()) + String.format("%-45s", this.getDescription()) + this.getId();
    }
}