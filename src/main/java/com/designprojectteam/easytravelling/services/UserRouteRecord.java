package com.designprojectteam.easytravelling.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designprojectteam.easytravelling.models.UserRouteRecorder;
import com.designprojectteam.easytravelling.repository.UserRouteRecorderRepository;

@Service
public class UserRouteRecord {
	
	@Autowired
	UserRouteRecorderRepository userRouteRecorderRepository;

	public void recordUserWithRouteDate(String userId) {
		UserRouteRecorder userRouteRecorder = new UserRouteRecorder();
		userRouteRecorder.setUserId(userId);
		userRouteRecorderRepository.save(userRouteRecorder);
	}
}
