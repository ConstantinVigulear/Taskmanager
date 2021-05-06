package com.stefanini.taskmanager.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IncorrectCommandFormatException extends Exception {
	private static final long serialVersionUID = 1L;
	static final Logger logger = LogManager.getLogger();

	public IncorrectCommandFormatException() {
        super();
        logger.error("Incorrect command format!");
        logger.info("Please refer to examples below. \n\tExamples:"
        		+ "\n\t- to create a new user:\n\t\tjava -jar myapplication.jar -createUser -fn='FirstName' -ln='LastName' -un='UserName'"
        		+ "\n\t- to show all users:\n\t\tjava -jar myapplication.jar -showAllUsers"
        		+ "\n\t- to add a new task:\n\t\tjava -jar myapplication.jar -addTask -tt='Task Title' -td='Task Description'"
        		+ "\n\t- to show all tasks:\n\t\tjava -jar myapplication.jar -showAllTasks"
        		+ "\n\t- to show user's tasks:\n\t\tjava -jar myapplication.jar -showUsersTasks -un='userName'"
        		+ "\n\t- to assign task to users:\n\t\tjava -jar myapplication.jar -assignTaskToUsers -tt='taskTitle' -un='userName'"
        		+ "\n\t- to show users on task:\n\t\tjava -jar myapplication.jar -showUsersOnTask -tt='taskTitle'"
        		+ "\n\t- to create user, task, assign:\n\t\tjava -jar myapplication.jar -createUserTaskAssign -fn='FirstName' -ln='LastName' -un='UserName' -tt='Task Title' -td='Task Description'");
    }
}