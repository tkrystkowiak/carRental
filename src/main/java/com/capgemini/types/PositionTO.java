package com.capgemini.types;

public class PositionTO {
	
	
	private long id;
	private String title;

	public PositionTO(long id, String title) {
		this.id = id;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
