package service;

import java.util.List;

import model.User;

public interface UserService {
	public boolean addUser(User user) throws Exception;
	public boolean updateUser(User user) throws Exception;
	public boolean select(User user)throws Exception;
	public List<User> getAllUsers() throws Exception;
}
