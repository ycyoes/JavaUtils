package com.ycyoes.demos.concurrency.art.entity;

public class User {
	private String name;
	private int old;
	
	public User(String name, int old) {
		this.name = name;
		this.old = old;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOld() {
		return old;
	}

	public void setOld(int old) {
		this.old = old;
	}
	
}
