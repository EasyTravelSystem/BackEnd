package com.designprojectteam.easytravelling.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.designprojectteam.easytravelling.models.ComplainUser;

public interface ComplainRepository extends MongoRepository<ComplainUser, String> {

}
