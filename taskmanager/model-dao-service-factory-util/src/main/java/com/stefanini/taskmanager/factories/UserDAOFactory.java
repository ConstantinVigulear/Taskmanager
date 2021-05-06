package com.stefanini.taskmanager.factories;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dao.UserDAOImpl;

// Concrete User DAO factory. Returns UserDAOImpl object as an implementation of UserDAO.
public class UserDAOFactory implements DAOFactory<UserDAO>{

	@Override
	public UserDAO getDAO() {
		return new UserDAOImpl();
	}
	
}
