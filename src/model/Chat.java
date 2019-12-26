package model;

public class Chat {
	private String name;
	private String time;
	private String text;
	private int chatId;

	public Chat(String name, String time, String text, int chatId) {
		super();
		this.name = name;
		this.time = time;
		this.text = text;
		this.chatId = chatId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Chat(String name, String time, String text) {
		super();
		this.name = name;
		this.time = time;
		this.text = text;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	
}
