package com.designprojectteam.easytravelling.helper;

import com.designprojectteam.easytravelling.models.User;

public class FilterUsers {

	private String routeFromTo;
	
	private User user;

	public String getRouteFromTo() {
		return routeFromTo;
	}

	public void setRouteFromTo(String routeFromTo) {
		this.routeFromTo = routeFromTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
