package com.stefanini.taskmanager.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.stefanini.taskmanager.models.Task;
import com.stefanini.taskmanager.models.User;

public interface UserDAO {
	
	// Create or insert user
	void insertUser(User user) throws SQLException;
	
	// Update user
	boolean updateUser(User user) throws SQLException;
	
	// Select user by id
	User selectUser(int id);
	
	// Select user by userName
	User selectUserByUserName(String userName);
	
	// select all users
	ArrayList<User> selectAllUsers();
	
	// Select user's tasks
	ArrayList<Task> selectUsersTasks(String userName) throws Exception;
	
	// Delete user
	boolean deleteUser(int id) throws SQLException;
}
