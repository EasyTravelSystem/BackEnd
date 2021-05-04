package com.designprojectteam.easytravelling.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.designprojectteam.easytravelling.helper.Coordinates;

@Document(collection = "routes")
public class RouteDirection {
	
	private List<Coordinates> routeApiRequests;

	public List<Coordinates> getRouteApiRequests() {
		return routeApiRequests;
	}

	public void setRouteApiRequests(List<Coordinates> routeApiRequests) {
		this.routeApiRequests = routeApiRequests;
	}

}
