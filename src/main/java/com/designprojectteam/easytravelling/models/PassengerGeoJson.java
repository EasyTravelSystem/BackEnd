package com.designprojectteam.easytravelling.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;

import com.designprojectteam.easytravelling.helper.Features;

//@Document(collection = "routes")
public class PassengerGeoJson {
	
	@Id
	private String id;

	//	@Field("collectionType")
	private String type;
	private List<Features> features;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Features> getFeatures() {
		return features;
	}
	public void setFeatures(List<Features> features) {
		this.features = features;
	}
	
}
