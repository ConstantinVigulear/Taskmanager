package com.stefanini.taskmanager.commands;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.models.Task;
import com.stefanini.taskmanager.services.UserService;

public class ShowUsersTasks implements Command {
	
	final static Logger logger = LogManager.getLogger(ShowUsersTasks.class);
	
	private String userName;
	
	public ShowUsersTasks(String userName) {
		this.userName = userName;
	}
	
	@Override
	public void execute() {
		try {
			ArrayList<Task> tasks = new UserService().selectUsersTasks(userName);

			if (tasks.isEmpty()) {
				logger.info("This user has no tasks.");
			} else {
				logger.info("User '" + userName + "' is assigned to the following tasks:");
				System.out.println(String.format("\n%-15s", "TITLE") + String.format("%-45s", "DESCRIPTION")
						+ String.format("%-5s", "ID") + "\n");
				for (Task task : tasks) {
					System.out.println(task.toString());
				}
				System.out.println("\n");
			}
		} catch (Exception exception) {
			//System.out.println("\nError: No such user exists!");
			logger.error("No such user exists!");
		}
	}
	
	@Override
	public void unexecute() {
		
	}

}
