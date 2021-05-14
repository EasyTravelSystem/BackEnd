package com.designprojectteam.easytravelling.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.designprojectteam.easytravelling.helper.Complains;
import com.designprojectteam.easytravelling.models.ComplainUser;
import com.designprojectteam.easytravelling.models.Profile;
import com.designprojectteam.easytravelling.models.User;
import com.designprojectteam.easytravelling.payload.response.MessageResponse;
import com.designprojectteam.easytravelling.repository.ComplainRepository;
import com.designprojectteam.easytravelling.repository.ProfileRepository;
import com.designprojectteam.easytravelling.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	ComplainRepository complainRepositroy;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

	
}
