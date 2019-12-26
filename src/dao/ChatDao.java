package dao;

import java.sql.SQLException;
import java.util.List;
import model.Chat;

public interface ChatDao {
	public int addChat(Chat chat) throws SQLException;
	public List<Chat> getAllChat() throws SQLException;
}
