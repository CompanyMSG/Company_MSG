package service;

import java.sql.SQLException;
import java.util.List;

import model.Chat;

public interface ChatService {
	public boolean addChat(Chat chat) throws SQLException;
	public List<Chat> getAllChat() throws SQLException;
}
