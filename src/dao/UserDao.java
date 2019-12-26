package dao;

import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserDao {
	public int addUser(User user) throws SQLException;
	public int updateUser(User user) throws SQLException;
	public boolean select(User user)throws SQLException;
	public List<User> getAllUsers() throws SQLException;
}
