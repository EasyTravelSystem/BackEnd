package com.designprojectteam.easytravelling.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.designprojectteam.easytravelling.models.ERole;
import com.designprojectteam.easytravelling.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);
}
