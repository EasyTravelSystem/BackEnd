package com.designprojectteam.easytravelling.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.designprojectteam.easytravelling.models.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {

	Optional<Profile> findByUserId(String userId);

	boolean existsByUserId(String userId);

}
