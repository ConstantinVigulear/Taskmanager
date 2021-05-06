package com.stefanini.taskmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.models.Task;
import com.stefanini.taskmanager.models.User;
import com.stefanini.taskmanager.services.UserService;
import com.stefanini.taskmanager.utils.JavaDataBaseConneciton;

//This DAO class provides CRUD database operations for the table users in the database.
public class TaskDAOImpl implements TaskDAO {
	
	final static Logger logger = LogManager.getLogger(TaskDAOImpl.class);

	private static final String INSERT_TASK_SQL = "insert into tasks (task_title, task_description) values (?, ?)";
	private static final String SELECT_TASK_BY_ID = "select task_id, task_title, task_descrption from tasks where task_id = ?";
	private static final String SELECT_TASK_BY_TASK_TITLE = "select task_id, task_title, task_description from tasks where task_title = ?";
	private static final String SELECT_ALL_TASKS = "select * from tasks";
	private static final String SELECT_USERS_ON_TASK = "select user_name as user from jobs j join users u on j.user_id = u.user_id join tasks t on j.task_id = t.task_id where task_title = ?";
	private static final String DELETE_TASKS_SQL = "delete from tasks where task_id = ?";
	private static final String UPDATE_TASKS_SQL = "update tasks set task_title = ?, task_description = ? where task_id = ?";
	private static final String ASSIGN_TASK_TO_USER = "insert into jobs (task_id, user_id) values((select task_id from tasks where task_title = ?), (select user_id from users where user_name = ?))";

	// Create or insert task
	@Override
	public void insertTask(Task task) throws SQLException {
		// The static method getConneciton from the type JavaDataBaseConnection should
		// be accessed in a static way

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_SQL)) {
			preparedStatement.setString(1, task.getTaskTitle());
			preparedStatement.setString(2, task.getDescription());
			preparedStatement.executeUpdate();
			logger.info("Success! Task has been added.");
		} catch (Exception ignored) {
			logger.error("Such task already exists!");
		}
	}

	// Update task
	@Override
	public boolean updateTask(Task task) throws Exception {
		boolean rowUpdated = false;

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASKS_SQL)) {

			if (selectTask(task.getId()) == null) {
				throw new Exception();
			} else {
				preparedStatement.setString(1, task.getTaskTitle());
				preparedStatement.setString(2, task.getDescription());
				preparedStatement.setInt(4, task.getId());

				rowUpdated = preparedStatement.executeUpdate() > 0;
			}
		}
		return rowUpdated;
	}

	// Select task by id
	@Override
	public Task selectTask(int id) {
		Task task = null;
		// Step 1. Establish connection

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				// Step 2. Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID)) {
			preparedStatement.setInt(1, id);
			// Step 3. Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();
			// Step 4. Process the ResultSet object
			while (resultset.next()) {
				String title = resultset.getString("task_title");
				String description = resultset.getString("task_description");
				task = new Task(title, description, id);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return task;
	}

	// Select task by task_title.
	@Override
	public Task selectTaskByTaskTitle(String taskTitle) throws Exception {
		Task task = null;
		// Step 1. Establish connection

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				// Step 2. Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_TASK_TITLE)) {
			preparedStatement.setString(1, taskTitle);
			// Step 3. Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();
			// Step 4. Process the ResultSet object
			while (resultset.next()) {
				String description = resultset.getString("task_description");
				int id = resultset.getInt("task_id");
				task = new Task(taskTitle, description, id);
			}
		}
		return task;
	}

	// Select all tasks
	@Override
	public ArrayList<Task> selectAllTasks() {
		ArrayList<Task> tasks = new ArrayList<>();

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS);) {
			ResultSet resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				String title = resultset.getString("task_title");
				String description = resultset.getString("task_description");
				int id = resultset.getInt("task_id");
				tasks.add(new Task(title, description, id));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return tasks;
	}

	// Delete task
	@Override
	public boolean deleteTask(int id) throws SQLException {
		boolean rowDeleted = false;

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASKS_SQL);) {

			if (selectTask(id) == null) {
				throw new Exception();
			} else {
				preparedStatement.setInt(1, id);
				rowDeleted = preparedStatement.executeUpdate() > 0;
			}
		} catch (Exception exception) {

		}
		return rowDeleted;
	}

	// Assign task to user
	@Override
	public void assignTaskToUser(String taskTitle, String userName) throws Exception {

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ASSIGN_TASK_TO_USER)) {
			try {
				if (selectTaskByTaskTitle(taskTitle) == null) {
					throw new Exception();
				}
			} catch (Exception exception) {
				logger.error("No such task exists!");
			}

			try {
				if (new UserService().selectUserByUserName(userName) == null) {
					throw new Exception();
				}
			} catch (Exception exception) {
				logger.error("No such user exists!");
			}
			
			preparedStatement.setString(1, taskTitle);
			preparedStatement.setString(2, userName);
			if (preparedStatement.executeUpdate() == 1) {
				logger.info("Succes! Task '" + taskTitle + "' has been assigned to user '" + userName + "'.");
			}
		} catch (Exception ignored) {
		}
	}

	// Show users on task
	@Override
	public ArrayList<User> showUsersOnTask(String taskTitle) throws Exception {
		ArrayList<User> users = new ArrayList<>();

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_ON_TASK)) {

			if (selectTaskByTaskTitle(taskTitle) == null) {
				throw new Exception();
			} else {
				preparedStatement.setString(1, taskTitle);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					String userName = resultSet.getString("user");
					users.add(new User(userName));
				}
			}
		}
		return users;
	}
}