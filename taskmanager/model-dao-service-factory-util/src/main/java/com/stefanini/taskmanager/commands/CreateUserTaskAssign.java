package com.stefanini.taskmanager.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.models.Task;
import com.stefanini.taskmanager.models.User;
import com.stefanini.taskmanager.services.TaskService;
import com.stefanini.taskmanager.services.UserService;

public class CreateUserTaskAssign implements Command {
	static final Logger logger = LogManager.getLogger();
	
	String firstName;
	String lastName;
	String userName;
	String taskTitle;
	String taskDescription;
	
	UserService userService;
	TaskService taskService;

	public CreateUserTaskAssign(String firstName, String lastName, String userName, String taskTitle,
			String taskDescription) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.userService = new UserService();
		this.taskService = new TaskService();
	}

	// In order to not allow one of inserts be executed I have to check if provided user or task already exist in data base.
	@Override
	public void execute() throws Exception {
		try {
			if (userService.selectUserByUserName(userName) != null) {
				logger.error("Error: Such user already exists!");
				throw new Exception();
			} else if (taskService.selectTaskByTaskTitle(taskTitle) != null) {
				logger.error("Error: Such task already exists!");
				throw new Exception();
			} else {
				userService.insertUser(new User(firstName, lastName, userName));
				taskService.insertTask(new Task(taskTitle, taskDescription));
				taskService.assignTaskToUser(taskTitle, userName);
			}
		} catch (Exception exception) {

		}

	}

	@Override
	public void unexecute() {

	}
}
