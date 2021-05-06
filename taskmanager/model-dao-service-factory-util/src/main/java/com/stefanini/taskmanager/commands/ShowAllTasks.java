package com.stefanini.taskmanager.commands;

import com.stefanini.taskmanager.models.Task;
import com.stefanini.taskmanager.services.TaskService;

public class ShowAllTasks implements Command {
	public ShowAllTasks() {

	}

	public void execute() {
		System.out.println(String.format("\n%-15s", "TITLE") + String.format("%-45s", "DESCRIPTION")
				+ String.format("%-5s", "ID") + "\n");
		for (Task task : new TaskService().selectAllTasks()) {
			System.out.println(task.toString());
		}
	}
	
	public void unexecute() {
		
	}
}
