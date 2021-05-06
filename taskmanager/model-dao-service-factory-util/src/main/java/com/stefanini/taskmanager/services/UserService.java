package com.stefanini.taskmanager.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.factories.Factory;
import com.stefanini.taskmanager.models.Task;
import com.stefanini.taskmanager.models.User;

public class UserService {
	private UserDAO userDAO;
	
	public UserService() {
		
		Factory.getInstance();
		this.userDAO = (UserDAO) Factory.getDAOFactory(UserDAO.class).getDAO();
	}

	// Create or insert user
	public void insertUser(User user) throws SQLException {
		userDAO.insertUser(user);
	}
	
	// Update user
	public boolean updateUser(User user) throws SQLException {
		return userDAO.updateUser(user);
	}
	
	// Select user by id
	public User selectUser(int id) {
		return userDAO.selectUser(id);
	}
	
	// Select user by user name.
	public User selectUserByUserName(String userName) throws Exception{
		return userDAO.selectUserByUserName(userName);
	}
	
	// Select all users
	public ArrayList<User> selectAllUsers() {
		return userDAO.selectAllUsers();
	}
	
	// Delete user
	public boolean deleteUser(int id) throws SQLException {
		return userDAO.deleteUser(id);
	}
	
	// Select user's tasks
		public ArrayList<Task> selectUsersTasks(String userName) throws Exception {
			return userDAO.selectUsersTasks(userName);
		}
	
}
