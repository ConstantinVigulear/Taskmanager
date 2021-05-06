package com.stefanini.taskmanager.factories;

import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dao.UserDAO;

// Factory to create DAOFactories. Singleton class. Eagerly initialized static instance guarantees thread safety.
public class Factory {
	
	// Private constructor to make Factory instance singleton - no other methods can instantiate a factory instance but Factory itself.
	private Factory() {
		
	}
	
	private static final Factory instance = new Factory();
	
	public static Factory getInstance() {
		return instance;
	}
	
	public static DAOFactory<?> getDAOFactory(Class<?> clazz) {
		if (clazz.equals(UserDAO.class)) {
			return new UserDAOFactory();
		}

		if (clazz.equals(TaskDAO.class)) {
			return new TaskDAOFactory();
		}
		return null;
	}
}
