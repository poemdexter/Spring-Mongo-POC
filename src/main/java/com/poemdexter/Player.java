package com.poemdexter;

import org.springframework.data.annotation.Id;

public class Player {

	@Id
	private String id;

	private String username;
	private String password;

	public Player() {
	}

	public Player(String username, String password) {
		this.username = username;
		this.password = password;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("Player [id=%s, username='%s', password='%s']", getId(), username, password);
	}
}