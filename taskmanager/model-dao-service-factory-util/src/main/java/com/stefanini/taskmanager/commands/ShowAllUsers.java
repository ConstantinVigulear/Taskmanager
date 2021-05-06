package com.stefanini.taskmanager.commands;

import com.stefanini.taskmanager.models.User;
import com.stefanini.taskmanager.services.UserService;

public class ShowAllUsers implements Command {
	public ShowAllUsers() {
		
	}
	
	public void execute() {
		System.out.println(String.format("\n%-15s", "FIRSTNAME") + String.format("%-15s", "LASTNAME")
				+ String.format("%-15s", "USERNAME") + "\n");
		for (User user : new UserService().selectAllUsers()) {
			System.out.println(user.toString());
		}
	}

	@Override
	public void unexecute() {

	}
}
