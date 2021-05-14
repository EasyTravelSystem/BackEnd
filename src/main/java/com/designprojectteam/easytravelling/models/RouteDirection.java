package com.designprojectteam.easytravelling.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.designprojectteam.easytravelling.helper.Coordinates;

@Document(collection = "routes")
public class RouteDirection {
	
	@Id
	private String id;
	
	private String userId;
	
	private String routeFromTo;

	private List<Coordinates> routeApiRequests;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getRouteFromTo() {
		return routeFromTo;
	}

	public void setRouteFromTo(String routeFromTo) {
		this.routeFromTo = routeFromTo;
	}

	public List<Coordinates> getRouteApiRequests() {
		return routeApiRequests;
	}

	public void setRouteApiRequests(List<Coordinates> routeApiRequests) {
		this.routeApiRequests = routeApiRequests;
	}

}
