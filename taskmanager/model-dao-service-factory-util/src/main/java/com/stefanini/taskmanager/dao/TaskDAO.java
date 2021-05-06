package com.stefanini.taskmanager.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.stefanini.taskmanager.models.Task;
import com.stefanini.taskmanager.models.User;

public interface TaskDAO {

	// Create or insert task
	void insertTask(Task task) throws SQLException;

	// Update a task
	boolean updateTask(Task task) throws Exception;

	// Select task by id
	Task selectTask(int id);
	
	// Select task by task_title.
	Task selectTaskByTaskTitle(String taskTitle) throws Exception;

	// Select all tasks
	ArrayList<Task> selectAllTasks();

	// Select users on task
	ArrayList<User> showUsersOnTask(String taskTitle) throws Exception;

	// Delete task
	boolean deleteTask(int id) throws SQLException;

	// Assign task to user
	void assignTaskToUser(String taskTitle, String userName) throws Exception;
}
