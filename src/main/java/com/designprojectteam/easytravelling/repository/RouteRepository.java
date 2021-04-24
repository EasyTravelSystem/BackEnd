package com.designprojectteam.easytravelling.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.designprojectteam.easytravelling.models.PassengerGeoJson;

public interface RouteRepository extends MongoRepository<PassengerGeoJson, String> {

}
