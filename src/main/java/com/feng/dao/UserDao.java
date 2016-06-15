package com.feng.dao;

import java.util.List;

import com.feng.model.User;

public interface UserDao {

	public void addUser(User user);
	
	public List<User> getAll();
	
	public List<User> getByName(String name);
	
	public List<User> getByName(String name, boolean isRefresh);
}
