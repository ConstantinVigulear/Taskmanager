package com.stefanini.taskmanager.mainapp;

import java.sql.*;

import com.stefanini.taskmanager.utils.IncorrectCommandFormatException;
import com.stefanini.taskmanager.utils.Invoker;

public class Taskmanager {
	

	public static void main(String[] args) throws Exception {

		try {
			if (args.length == 0)
				throw new IncorrectCommandFormatException();

			// Invokes CommandService and passes arguments to it.
			Invoker invoker = new Invoker(args);

			switch (args[0]) {
			case "-createUser" -> {
				// Validating command format
				if (args.length != 4) {
					throw new IncorrectCommandFormatException();
				} else {
					invoker.commandCreateUser();
				}
				break;
			}
			case "-showAllUsers" -> {
				invoker.commandShawAllUser();
				break;
			}
			case "-addTask" -> {
				try {
					// Validating command format
					if (args.length != 3) {
						throw new IncorrectCommandFormatException();
					} else {
						try {
							invoker.commandAddTask();
						} catch (SQLException exception) {
							exception.printStackTrace();
						}
					}
				} catch (IncorrectCommandFormatException ignored) {

				}
				break;
			}
			case "-showAllTasks" -> {
				invoker.commandShowAllTasks();
				break;
			}
			case "-showUsersTasks" -> {
				try {
					// Validating command format
					if (args.length != 2) {
						throw new IncorrectCommandFormatException();
					} else {
						invoker.commandShowUsersTasks();
					}
				} catch (IncorrectCommandFormatException ignored) {
				}

				break;
			}
			case "-assignTaskToUser" -> {
				try {
					// Validating command format
					if (args.length != 3) {
						throw new IncorrectCommandFormatException();
					} else {
						invoker.commandAssignTaskToUser();
					}

				} catch (IncorrectCommandFormatException ignored) {
				}
				break;
			}
			case "-showUsersOnTask" -> {
				invoker.commandShowUsersOnTask();
				break;
			}
			case "-createUserTaskAssign" -> {
				try {
					// Validating command format
					if (args.length != 6) {
						throw new IncorrectCommandFormatException();
					} else {
						invoker.commandCreatUserTaskAssign();
					}

				} catch (IncorrectCommandFormatException ignored) {
				}
				break;
			}
			default -> {
				throw new IncorrectCommandFormatException();
			}
			}
		} catch (IncorrectCommandFormatException ignored) {
		}
	}
}