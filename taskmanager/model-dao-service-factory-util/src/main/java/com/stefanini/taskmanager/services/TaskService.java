package com.stefanini.taskmanager.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.factories.Factory;
import com.stefanini.taskmanager.models.Task;
import com.stefanini.taskmanager.models.User;

public class TaskService {
	private TaskDAO taskDAO;
	
	public TaskService() {

		Factory.getInstance();
		this.taskDAO = (TaskDAO) Factory.getDAOFactory(TaskDAO.class).getDAO();
	}

	// Create or insert a task
	public void insertTask(Task task) throws SQLException {
		taskDAO.insertTask(task);
	}

	// Update a task
	public boolean updateTask(Task task) throws Exception {
		return taskDAO.updateTask(task);
	}

	// Select task by id
	public Task selectTask(int id) {
		return taskDAO.selectTask(id);
	}
	
	// Select task by task title.
	public Task selectTaskByTaskTitle(String taskTitle) throws Exception {
		return taskDAO.selectTaskByTaskTitle(taskTitle);
	}

	// Select all tasks
	public ArrayList<Task> selectAllTasks() {
		return taskDAO.selectAllTasks();
	}

	// Show users on task
	public ArrayList<User> showUsersOnTask(String taskTitle) throws Exception {
		return taskDAO.showUsersOnTask(taskTitle);
	}

	// Delete a task
	public boolean deleteTask(int id) throws SQLException {
		return taskDAO.deleteTask(id);
	}

	// Assign task to user
	public void assignTaskToUser(String taskTitle, String userName) throws Exception {
		taskDAO.assignTaskToUser(taskTitle, userName);
	}
}
