package model;

public class User {
	private int id;
	private String Username;
	private String password;
	private String sex;
	private String Nickname;
	private boolean onLine;
	public User(int id, String username, String password, String sex, String nickname, boolean onLine) {
		super();
		this.id = id;
		Username = username;
		this.password = password;
		this.sex = sex;
		Nickname = nickname;
		this.onLine = onLine;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return Username;
	}
	public User(String username, String password) {
		super();
		Username = username;
		this.password = password;
	}

	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public User(int id, String username, String password, String sex, String nickname) {
		super();
		this.id = id;
		Username = username;
		this.password = password;
		this.sex = sex;
		Nickname = nickname;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", Username=" + Username + ", password=" + password + ", sex=" + sex + ", Nickname="
				+ Nickname + "]";
	}

	public boolean isOnLine() {
		return onLine;
	}

	public void setOnLine(boolean onLine) {
		this.onLine = onLine;
	}
	
}
