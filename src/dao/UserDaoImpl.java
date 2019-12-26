package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.JDBCUtils;

public class UserDaoImpl implements UserDao {
	@Override
	public int addUser(User user) throws SQLException {
		int num = 0;
		String sql = "";
		try{
			sql = "insert into user(username,password,sex,nickname) values(?,?,?,?)";
			num = JDBCUtils.executeUpdate(sql,user.getUsername(),user.getPassword(),user.getSex(),user.getNickname());
		}catch(Exception e){
			throw e;
		}finally{
			sql = null;
		}
		return num;
	}

	@Override
	public int updateUser(User user) throws SQLException {
		int num = 0;
		String sql = "";
		try{
			sql = "update user set onLine=?where Username=?";
			num = JDBCUtils.executeUpdate(sql,user.isOnLine(),user.getUsername());
		}catch(Exception e){
			throw e;
		}finally{
			sql = null;
		}
		return num;
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		List<User> list = new ArrayList<User>();
		String sql = "";
		ResultSet rs = null;
		try{
			sql = "select * from user";
			rs = JDBCUtils.executeQuery(sql);
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setNickname(rs.getString("nickname"));
				user.setOnLine(rs.getBoolean("onLine"));
				list.add(user);
			}
		}catch(Exception e){
			throw e;
		}finally{
			JDBCUtils.closeAll(null, null, rs);
			sql = null;
		}
		return list;
	}

	@Override
	public boolean select(User user) throws SQLException {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		String sql = "";
		boolean flag=false;
		ResultSet rs = null;
		try{
			sql = "select * from user where Username=? and password=?";
			rs = JDBCUtils.executeQuery(sql,user.getUsername(),user.getPassword());
			if(rs.next()) {
				flag= true;
			}else {
				flag=false;
			}
		}catch(Exception e){
			throw e;
		}finally{
			JDBCUtils.closeAll(null, null, rs);
			sql = null;
		}
		return flag;
	}
	
}
