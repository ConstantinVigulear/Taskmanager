package com.stefanini.taskmanager.commands;

import java.sql.SQLException;



import com.stefanini.taskmanager.models.Task;
import com.stefanini.taskmanager.services.TaskService;

public class AddTask implements Command {
	
	
	private String taskTitle;
	private String taskDescription;

	public AddTask(String taskTitle, String taskDescription) {
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
	}

	@Override
	public void execute() throws SQLException {
		new TaskService().insertTask(new Task(taskTitle, taskDescription));
	}

	@Override
	public void unexecute() {

	}
}
