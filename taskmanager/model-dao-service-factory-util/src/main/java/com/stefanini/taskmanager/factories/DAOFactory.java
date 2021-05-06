package com.stefanini.taskmanager.factories;

// Interface for concrete DAO factories.
public interface DAOFactory<T> {
	public T getDAO();
}
