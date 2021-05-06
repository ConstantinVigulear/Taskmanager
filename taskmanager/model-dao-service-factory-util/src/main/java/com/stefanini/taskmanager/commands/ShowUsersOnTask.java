package com.stefanini.taskmanager.commands;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.models.User;
import com.stefanini.taskmanager.services.TaskService;

public class ShowUsersOnTask implements Command {
	static final Logger logger = LogManager.getLogger();
	
	private String taskTitle;

	public ShowUsersOnTask(String tastTitle) {
		this.taskTitle = tastTitle;
	}

	@Override
	public void execute() throws Exception {
		try {
			ArrayList<User> users = new TaskService().showUsersOnTask(taskTitle);

			if (users.isEmpty()) {
				logger.info("No one tackles this task.");
			} else {
				logger.info("The following users are assigned to the task '" + taskTitle + "':");
				for (User user : users) {
					System.out.println(user.getUserName());
				}
			}
		} catch (Exception exception) {
			logger.error("No such task exists!");
		}
	}
	
	@Override
	public void unexecute() {
		
	}

}
