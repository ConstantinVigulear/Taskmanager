package com.stefanini.taskmanager.utils;

import com.stefanini.taskmanager.services.CommandService;


// An invoker class that invokes CommandService and passes arguments from main to it.
public class Invoker {

	private CommandService commandSevice;
	
	public Invoker(String[] args) {
		this.commandSevice = new CommandService(args);
	}
	
	public void commandAddTask() throws Exception{
		this.commandSevice.commandAddTask();
	}
	
	public void commandShowAllTasks() throws Exception {
		this.commandSevice.commandShowAllTasks();
	}
	
	public void commandShowUsersOnTask() throws Exception {
		this.commandSevice.commandShowUsersOnTask();
	}
	
	public void commandAssignTaskToUser() throws Exception{
		this.commandSevice.commandAssignTaskToUser();
	}
	
	public void commandCreateUser() throws Exception {
		this.commandSevice.commandCreateUser();
	}
	
	public void commandShawAllUser() throws Exception {
		this.commandSevice.commandShowAllUser();
	}
	
	public void commandShowUsersTasks() throws Exception {
		this.commandSevice.commandShowUsersTasks();
	}
	
	public void commandCreatUserTaskAssign() throws Exception {
		this.commandSevice.commandCreateUserTaskAssign();
	}
}
