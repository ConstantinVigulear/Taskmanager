package com.stefanini.taskmanager.factories;

import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dao.TaskDAOImpl;

// Concrete Task DAO factory. Returns TaskDAOImpl object as an implementation of TaskDAO.
public class TaskDAOFactory implements DAOFactory<TaskDAO> {

	@Override
	public TaskDAO getDAO() {
		return new TaskDAOImpl();
	}

}
