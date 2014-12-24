package com.suckoo.cuckoo.weather.model;

public class User {
	public final static String KEY_ID =  "id";
	public final static String KEY_NAME = "name";
    public final static String KEY_PASSWORD = "password";
    public final static String KEY_EMAIL = "email";
    public final static String KEY_NICKNAME = "nickname";
    public final static String KEY_AVATAR = "avatar";
    
	private String id;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private byte avatar;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public byte getAvatar() {
		return avatar;
	}
	public void setAvatar(byte avatar) {
		this.avatar = avatar;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + ", nickname=" + nickname
				+ ", avatar=" + avatar + "]";
	}
}
