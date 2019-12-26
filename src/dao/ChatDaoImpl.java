package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.Chat;
import util.JDBCUtils;

public class ChatDaoImpl implements ChatDao{

	@Override
	public int addChat(Chat chat) throws SQLException {
		// TODO Auto-generated method stub
		int num = 0;
		String sql = "";
		try{
			sql = "insert into chat(name,time,text,id,to) values(?,now(),?,1,?)";
			num = JDBCUtils.executeUpdate(sql,chat.getName(),chat.getText(),chat.getTo());
		}catch(Exception e){
			throw e;
		}finally{
			sql = null;
		}
		return num;
	}

	@Override
	public List<Chat> getAllChat() throws SQLException {
		// TODO Auto-generated method stub
		List<Chat> list=new ArrayList<Chat>();
		String sql="";
		ResultSet rs=null;
		try {
			sql="select *from chat";
			rs=JDBCUtils.executeQuery(sql);
			while(rs.next()) {
				Chat chat=new Chat();
				chat.setName(rs.getString("name"));
				chat.setTime(rs.getString("time"));
				chat.setText(rs.getString("text"));
				chat.setChatId(rs.getInt("chatId"));
				chat.setTo(rs.getString("to"));
				list.add(chat);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally {
			JDBCUtils.closeAll(null, null, rs);
			sql = null;
		}
		
		return list;
	}


}
