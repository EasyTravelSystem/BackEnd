package com.designprojectteam.easytravelling.repository;

import com.designprojectteam.easytravelling.models.ComplainUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplainRepository extends MongoRepository<ComplainUser, String> {
}
