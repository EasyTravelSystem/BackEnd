package com.designprojectteam.easytravelling.controller;

import com.designprojectteam.easytravelling.helper.Complains;
import com.designprojectteam.easytravelling.models.ComplainUser;
import com.designprojectteam.easytravelling.models.User;
import com.designprojectteam.easytravelling.payload.response.MessageResponse;
import com.designprojectteam.easytravelling.repository.ComplainRepository;
import com.designprojectteam.easytravelling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ComplainRepository complainRepository;

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

	@GetMapping("/findUserByUsername")
	public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
		if (userRepository.existsByUsername(username)) {
			User user = userRepository.findByUsername(username).get();
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.ok(new MessageResponse("Not Found"));
	}
	@PostMapping("/removeUser")
	public ResponseEntity<?> removeUser(@RequestParam String userId) {
		userRepository.deleteById(userId);
		return ResponseEntity.ok(new MessageResponse("Removed"));
	}
	@GetMapping("/getUserProfile")
	public ResponseEntity<?> getProfileInfo(@RequestParam String userId) {
		if (profileRepository.existsByUserId(userId)) {
			Profile profile = profileRepository.findByUserId(userId).get();
			return ResponseEntity.ok(profile);
		} else {
			return ResponseEntity.ok(new MessageResponse("No Value Present"));
		}
	}
	@GetMapping("/getComplains")
	public ResponseEntity<?> getComplains() {
		List<Complains> complainList = new ArrayList<Complains>();
		List<ComplainUser> allComplains = complainRepositroy.findAll();
		for(ComplainUser complain:allComplains) {
			User user = userRepository.findById(complain.getUserId()).get();
			Complains complains = new Complains();
			complains.setUser(user);
			complains.setComplain(complain.getComplain());
			complainList.add(complains);
		}
		return ResponseEntity.ok(complainList);
	}

}
