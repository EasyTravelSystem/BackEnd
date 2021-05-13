package com.designprojectteam.easytravelling.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.designprojectteam.easytravelling.models.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {

}
