package vn.com.fpt.mockproject.dao;

import java.util.List;

import vn.com.fpt.mockproject.model.User;

public interface UserDAO {
	public User getUser(String userId);
	public List<User> getAllUser(int start, int end);
	public void insertUser(User user);
	public int getSize();
	public String getUserGroup(String userId);
}
