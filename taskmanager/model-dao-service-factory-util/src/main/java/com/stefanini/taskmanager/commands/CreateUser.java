package com.stefanini.taskmanager.commands;

import java.sql.SQLException;

import com.stefanini.taskmanager.models.User;
import com.stefanini.taskmanager.services.UserService;

public class CreateUser implements Command {
	private String firstName;
	private String lastName;
	private String userName;

	public CreateUser(String firstName, String lastName, String userName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
	}

	@Override
	public void execute() throws SQLException {
		new UserService().insertUser(new User(firstName, lastName, userName));
	}

	public void unexecute() {

	}
}
