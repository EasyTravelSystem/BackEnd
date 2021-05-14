package com.designprojectteam.easytravelling.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.designprojectteam.easytravelling.helper.Coordinates;

@Service
public class GMapRouteJsonToObject {
	
	List<Coordinates> coordinatesList;

	public List<Coordinates> jsonStringToCoordinates(String json) {
		
		coordinatesList = new ArrayList<Coordinates>();
		
		String words1 = json.replace("[", "").replace("]", "").replace(" ", "");
		String[] words = words1.split(",");
		for(String word : words) {
			String[] split = word.split("/");
			Coordinates coordinates = new Coordinates();
			coordinates.setLatitude(split[0].replace("lat:", ""));
			coordinates.setLongitude(split[1].replace("longitude:", ""));
			coordinatesList.add(coordinates);
		}
		
		return coordinatesList;
	}
}
