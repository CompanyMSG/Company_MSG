package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ChatDao;
import dao.ChatDaoImpl;
import model.Chat;
import model.User;
import util.JDBCUtils;

public class ChatServiceImpl  implements ChatService{
	private ChatDao chatDao=new ChatDaoImpl();
	@Override
	public boolean addChat(Chat chat) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection conn = null;
		int num = 0;
		try{
			conn = JDBCUtils.getConnection();
			num = chatDao.addChat(chat);
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
	public List<Chat> getAllChat() throws SQLException {
		// TODO Auto-generated method stub
		List<Chat> list = null;
		Connection conn = null;
		try{
			conn = JDBCUtils.getConnection();
			list = chatDao.getAllChat();
		}catch(Exception e){
			throw e;
		}finally{
			JDBCUtils.closeAll(conn, null, null);
			conn = null;
		}
		return list;
	}

}
