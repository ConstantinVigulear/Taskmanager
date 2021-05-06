package com.stefanini.taskmanager.commands;

import com.stefanini.taskmanager.services.TaskService;

public class AssignTaskToUser implements Command {
	private String taskTitle;
	private String userName;
	
	public AssignTaskToUser(String taskTitle, String userNane) {
		this.taskTitle = taskTitle;
		this.userName = userNane;
	}
	
	@Override
	public void execute() throws Exception {
			new TaskService().assignTaskToUser(taskTitle, userName);
	}
	
	@Override
	public void unexecute() {
		
	}
}