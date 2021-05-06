package com.designprojectteam.easytravelling.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.designprojectteam.easytravelling.models.RouteDirection;

public interface RouteRepository extends MongoRepository<RouteDirection, String> {

	void deleteByUserId(String userId);

}
