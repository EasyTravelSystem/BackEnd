package com.designprojectteam.easytravelling.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.designprojectteam.easytravelling.models.UserRouteRecorder;

public interface UserRouteRecorderRepository extends MongoRepository<UserRouteRecorder, String> {
	List<Object> findByCreatedDateBetween(LocalDate start,LocalDate end);
}
