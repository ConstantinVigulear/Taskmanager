package com.stefanini.taskmanager.services;

import com.stefanini.taskmanager.commands.AddTask;
import com.stefanini.taskmanager.commands.AssignTaskToUser;
import com.stefanini.taskmanager.commands.Command;
import com.stefanini.taskmanager.commands.CreateUser;
import com.stefanini.taskmanager.commands.CreateUserTaskAssign;
import com.stefanini.taskmanager.commands.ShowAllTasks;
import com.stefanini.taskmanager.commands.ShowAllUsers;
import com.stefanini.taskmanager.commands.ShowUsersOnTask;
import com.stefanini.taskmanager.commands.ShowUsersTasks;

// A Command Service class that takes arguments from class, initializes command classes with arguments, and allows call to certain command.

public class CommandService {
	
	String arg1;
	String arg2;
	String arg3;
	String arg4;
	String arg5;

	private Command addTask;
	private Command showAllTasks;
	private Command showUsersOnTask;
	private Command assignUsersToTask;
	private Command createUser;
	private Command showAllUsers;
	private Command showUsersTasks;
	private Command createUserTaskAssign;

	public CommandService(String[] args) {
		if (args.length > 0) {
			this.showAllTasks = new ShowAllTasks();
			this.showAllUsers = new ShowAllUsers();
		}
		if (args.length > 1) {
			this.arg1 = args[1].substring(5, args[1].length() - 1);
			this.showUsersOnTask = new ShowUsersOnTask(arg1);
			this.showUsersTasks = new ShowUsersTasks(arg1);
		}
		if (args.length > 2) {
			this.arg2 = args[2].substring(5, args[2].length() - 1);
			this.addTask = new AddTask(arg1, arg2);
			this.assignUsersToTask = new AssignTaskToUser(arg1, arg2);
		}
		if (args.length > 3) {
			this.arg3 = args[3].substring(5, args[3].length() - 1);
			this.createUser = new CreateUser(arg1, arg2, arg3);
		}
		if (args.length > 5) {
			this.arg4 = args[4].substring(5, args[4].length() - 1);
			this.arg5 = args[5].substring(5, args[5].length() - 1);
			this.createUserTaskAssign = new CreateUserTaskAssign(arg1, arg2, arg3, arg4, arg5);
		}
	}

	public void commandAddTask() throws Exception {
		this.addTask.execute();
	}

	public void commandShowAllTasks() throws Exception {
		this.showAllTasks.execute();
	}

	public void commandShowUsersOnTask() throws Exception {
		this.showUsersOnTask.execute();
	}

	public void commandAssignTaskToUser() throws Exception {
		this.assignUsersToTask.execute();
	}

	public void commandCreateUser() throws Exception {
		this.createUser.execute();
	}

	public void commandShowAllUser() throws Exception {
		this.showAllUsers.execute();
	}

	public void commandShowUsersTasks() throws Exception {
		this.showUsersTasks.execute();
	}
	
	// This command combines 3 different commands.
	public void commandCreateUserTaskAssign() throws Exception {
		this.createUserTaskAssign.execute();
	}
}