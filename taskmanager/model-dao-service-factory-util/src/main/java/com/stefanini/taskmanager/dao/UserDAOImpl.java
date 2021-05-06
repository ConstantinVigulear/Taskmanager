package com.stefanini.taskmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.stefanini.taskmanager.models.Task;
import com.stefanini.taskmanager.models.User;
import com.stefanini.taskmanager.utils.JavaDataBaseConneciton;

// This DAO class provides CRUD database operations for the table users in the database.
public class UserDAOImpl implements UserDAO {
	
	private static final String INSERT_USERS_SQL = "insert into users (first_name, last_name, user_name) values (?, ?, ?)";
	private static final String SELECT_USER_BY_ID = "select user_id, first_name, last_name, user_name from users where user_id = ?";
	private static final String SELECT_USER_BY_USERNAME = "select user_id, first_name, last_name from users where user_name = ?";
	private static final String SELECT_ALL_USER = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?";
	private static final String SELECT_USERS_TASKS = "SELECT t.task_title , t.task_description, t.task_id FROM jobs j join users u on j.user_id = u.user_id join tasks t on j.task_id = t.task_id where u.user_name = ?";
	private static final String UPDATE_USERS_SQL = "update users set first_name = ?, last_name = ?, user_name = ? where id = ?";

	// Create or insert user
	@Override
	public void insertUser(User user) throws SQLException {
		// JavaDataBaseConneciton.getInstance();
		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getUserName());
			preparedStatement.executeUpdate();
			System.out.println("\nSuccess! User has been created.");
		} catch (Exception ignored) {
			System.out.println("\nError: Such user already exists!");
		}
	}

	// Update user
	@Override
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated = false;
		
		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {

			if (selectUser(user.getUserId()) == null) {
				throw new Exception();
			} else {
				preparedStatement.setString(1, user.getFirstName());
				preparedStatement.setString(2, user.getLastName());
				preparedStatement.setString(3, user.getUserName());
				preparedStatement.setInt(4, user.getUserId());

				rowUpdated = preparedStatement.executeUpdate() > 0;
			}
		} catch (Exception exception) {
			System.out.println("\nError: No such user exists!");
		}
		return rowUpdated;
	}

	// Select user by id
	@Override
	public User selectUser(int id) {
		User user = null;
		// Step 1. Establish connection

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				// Step 2. Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
			preparedStatement.setInt(1, id);
			// Step 3. Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();

			// Step 4. Process the ResultSet object
			while (resultset.next()) {
				String firstName = resultset.getString("first_name");
				String lastName = resultset.getString("last_name");
				String userName = resultset.getString("user_name");
				user = new User(firstName, lastName, userName, id);
			}
		} catch (Exception exception) {
			System.out.println("\nError: No such user exists!");
		}
		return user;
	}

	// Select user by userName
	@Override
	public User selectUserByUserName(String userName) {
		User user = null;

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				int id = resultSet.getInt("user_id");
				user = new User(firstName, lastName, userName, id);
			}
		} catch (Exception exception) {
			System.out.println("\nError: No such user exists!");
		}
		return user;
	}

	// Select all users
	@Override
	public ArrayList<User> selectAllUsers() {
		ArrayList<User> users = new ArrayList<>();

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);) {
			ResultSet resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				String firstName = resultset.getString("first_name");
				String lastName = resultset.getString("last_name");
				String userName = resultset.getString("user_name");
				int id = resultset.getInt("user_id");
				users.add(new User(firstName, lastName, userName, id));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return users;
	}

	// Delete user
	@Override
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted = false;

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);) {

			if (selectUser(id) == null) {
				throw new SQLException();
			} else {
				preparedStatement.setInt(1, id);
				rowDeleted = preparedStatement.executeUpdate() > 0;
			}
		} catch (Exception exception) {
			System.out.println("\nError: No such user exists!");
		}
		return rowDeleted;
	}

	// Select user's tasks
	@Override
	public ArrayList<Task> selectUsersTasks(String userName) throws Exception {
		ArrayList<Task> tasks = new ArrayList<>();

		try (Connection connection = JavaDataBaseConneciton.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_TASKS);) {
			if (selectUserByUserName(userName) == null) {
				throw new Exception();
			} else {
				preparedStatement.setString(1, userName);
				ResultSet resultset = preparedStatement.executeQuery();

				while (resultset.next()) {
					String title = resultset.getString("task_title");
					String description = resultset.getString("task_description");
					int id = resultset.getInt("task_id");
					tasks.add(new Task(title, description, id));
				}
			}
		} 
		return tasks;
	}
}