package model;

public class Chat {
	private String name;
	private String time;
	private String text;
	private int chatId;
	private String toname;
	public Chat(String name, String time, String text, String toname) {
		super();
		this.name = name;
		this.time = time;
		this.text = text;
		this.toname = toname;
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
	public String getToname() {
		return toname;
	}
	public void setTo(String toname) {
		this.toname = toname;
	}
	@Override
	public String toString() {
		return "Chat [name=" + name + ", time=" + time + ", text=" + text + ", chatId=" + chatId + ", toname=" + toname
				+ "]";
	}

}
