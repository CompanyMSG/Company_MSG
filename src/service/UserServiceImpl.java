package service;

import java.sql.Connection;
import java.util.List;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import util.JDBCUtils;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();
	@Override
	public boolean addUser(User user) throws Exception {
		boolean flag = false;
		Connection conn = null;
		int num = 0;
		try{
			conn = JDBCUtils.getConnection();
			num = userDao.addUser(user);
			if(num == 1){
				flag = true;
			}
		}catch(Exception e){
			throw e;
		}finally{
			JDBCUtils.closeAll(conn,null,null);
			conn = null;
		}
		return flag;
	}

	@Override
	public boolean updateUser(User user) throws Exception {
		boolean flag = false;
		Connection conn = null;
		int num = 0;
		try{
			conn = JDBCUtils.getConnection();
			num = userDao.updateUser(user);
			if(num == 1){
				flag = true;
			}
		}catch(Exception e){
			throw e;
		}finally{
			JDBCUtils.closeAll(conn,null,null);
			conn = null;
		}
		return flag;
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		System.out.println("getAllUsers·½·¨");
		List<User> list = null;
		Connection conn = null;
		try{
			conn = JDBCUtils.getConnection();
			list = userDao.getAllUsers();
		}catch(Exception e){
			throw e;
		}finally{
			JDBCUtils.closeAll(conn, null, null);
			conn = null;
		}
		return list;
	}

	@Override
	public boolean select(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection conn = null;
		try{
			conn = JDBCUtils.getConnection();
			flag = userDao.select(user);
		}catch(Exception e){
			throw e;
		}finally{
			JDBCUtils.closeAll(conn,null,null);
			conn = null;
		}
		return flag;
	}

}
