package com.stefanini.taskmanager.models;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private int userId;

    public User(String firstName, String lastName, String userName, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userId = userId;
    }
    
    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }
    
    public User(String userName) {
    	this.userName = userName;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setUserId(int userId) {
    	this.userId = userId;
    }
    
    public int getUserId() {
		return userId;
	}

    @Override
    public String toString() {
        return String.format("%-15s", this.getFirstName()) + String.format("%-15s", this.getLastName()) + String.format("%-15s", this.getUserName()) + this.getUserId();
    }
}