package com.designprojectteam.easytravelling.payload.response;

import java.util.List;

import com.designprojectteam.easytravelling.models.User;

public class FilterFriendResponse {

	private String route;
	
	private List<User> userList;
	
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
