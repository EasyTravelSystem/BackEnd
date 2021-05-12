package com.designprojectteam.easytravelling.payload.response;

import java.util.List;

import com.designprojectteam.easytravelling.helper.FilterUsers;

public class FilterFriendResponse {
	
	private List<FilterUsers> userList;
	
	public List<FilterUsers> getUserList() {
		return userList;
	}
	public void setUserList(List<FilterUsers> userList) {
		this.userList = userList;
	}
}
