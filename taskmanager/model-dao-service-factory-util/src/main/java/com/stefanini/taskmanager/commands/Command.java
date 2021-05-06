package com.stefanini.taskmanager.commands;

public interface Command {

	void execute() throws Exception;
	void unexecute();
}
