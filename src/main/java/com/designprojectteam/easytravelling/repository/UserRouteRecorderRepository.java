package com.designprojectteam.easytravelling.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.designprojectteam.easytravelling.models.UserRouteRecorder;

public interface UserRouteRecorderRepository extends MongoRepository<UserRouteRecorder, String> {

}
