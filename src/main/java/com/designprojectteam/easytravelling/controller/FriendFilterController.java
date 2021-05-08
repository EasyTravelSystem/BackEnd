package com.designprojectteam.easytravelling.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.designprojectteam.easytravelling.helper.Coordinates;
import com.designprojectteam.easytravelling.models.RouteDirection;
import com.designprojectteam.easytravelling.models.User;
import com.designprojectteam.easytravelling.payload.request.RouteApiRequest;
import com.designprojectteam.easytravelling.payload.response.MessageResponse;
import com.designprojectteam.easytravelling.repository.RouteRepository;
import com.designprojectteam.easytravelling.repository.UserRepository;
import com.designprojectteam.easytravelling.services.GMapRouteJsonToObject;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/geo")
public class FriendFilterController {

	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GMapRouteJsonToObject gMapRouteJsonToObject;

	@GetMapping("/allRoutes")
	public ResponseEntity<?> getAllRoutes() {

//		List<RouteDirection> findAll = routeRepository.findAll();
		Optional<User> findById = userRepository.findById("605e27d5674f487e21eab147");

		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping("/saveDirection")
	public ResponseEntity<?> saveMapDirection(@RequestBody RouteApiRequest json) {
		List<Coordinates> jsonStringToCoordinates = gMapRouteJsonToObject.jsonStringToCoordinates(json.getData());
		RouteDirection routeDirection = new RouteDirection();
		routeDirection.setRouteApiRequests(jsonStringToCoordinates);
		routeDirection.setUserId(json.getUserId());
		routeRepository.save(routeDirection);
		return ResponseEntity.ok(routeDirection);
	}
	
	@PostMapping("/map")
	public ResponseEntity<?> routeApi(@RequestBody RouteApiRequest json) {
		
//		List<Coordinates> coordinatesList = new ArrayList<Coordinates>();
//		
//		String words1 = json.getData().replace("[", "").replace("]", "").replace(" ", "");
//		String[] words = words1.split(",");
//		for(String word : words) {
//			String[] split = word.split("/");
//			Coordinates coordinates = new Coordinates();
//			coordinates.setLatitude(split[0].replace("lat:", ""));
//			coordinates.setLongitude(split[1].replace("longitude:", ""));
//			coordinatesList.add(coordinates);
//		}
//		Pattern pattern = Pattern.compile(" ");
//        words = pattern.split(json.getData());
//		String words = json.getData().replace("\"", "");
		
		List<Coordinates> jsonStringToCoordinates = gMapRouteJsonToObject.jsonStringToCoordinates(json.getData());
		
		return ResponseEntity.ok(jsonStringToCoordinates);
	}
	
	@PostMapping("/getSameRoute")
	public ResponseEntity<?> findSaveRoute(@RequestBody RouteApiRequest json) {
		
		List<String> userIdList = new ArrayList<String>();
		List<User> userList = new ArrayList<User>();
		
		List<Coordinates> jsonStringToCoordinates = gMapRouteJsonToObject.jsonStringToCoordinates(json.getData());
		
//		 Save current route in db
		RouteDirection currentUserRouteDirection = new RouteDirection();
		currentUserRouteDirection.setRouteApiRequests(jsonStringToCoordinates);
		currentUserRouteDirection.setUserId(json.getUserId());
		routeRepository.save(currentUserRouteDirection);
		
		List<RouteDirection> allRouteDirection = routeRepository.findAll();
		
		for(RouteDirection routeDirection : allRouteDirection) {
			int count = 0;
			if(jsonStringToCoordinates.size() <= routeDirection.getRouteApiRequests().size()) {
				for(Coordinates coordinatesFromRequest:jsonStringToCoordinates) {
					for(Coordinates coordinatesFromApi: routeDirection.getRouteApiRequests()) {
						if(coordinatesFromRequest.getLatitude().equals(coordinatesFromApi.getLatitude()) && coordinatesFromRequest.getLongitude().equals(coordinatesFromApi.getLongitude())) {
							count++;
						}
					}
				}
				if (count == jsonStringToCoordinates.size()) {
					userIdList.add(routeDirection.getUserId());
				}
			} else {
				for(Coordinates coordinatesFromApi: routeDirection.getRouteApiRequests()) {
					for(Coordinates coordinatesFromRequest:jsonStringToCoordinates) {
						if(coordinatesFromApi.getLatitude().equals(coordinatesFromRequest.getLatitude()) && coordinatesFromApi.getLongitude().equals(coordinatesFromRequest.getLongitude())) {
							count++;
						}
					}
				}
				if (count == routeDirection.getRouteApiRequests().size()) {
					userIdList.add(routeDirection.getUserId());
				}
			}
		}
		
		for(String userId:userIdList) {
			User user = userRepository.findById(userId).get();
			if(!user.getId().equals(json.getUserId()))
				userList.add(user);
		}
		
		return ResponseEntity.ok(userList);
	}
	
	@PostMapping("/removeRoute")
	public ResponseEntity<?> removeRouteFromDb(@RequestParam("userId") String userId) {
		routeRepository.deleteByUserId(userId);
		return ResponseEntity.ok(new MessageResponse("success"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@PostMapping("/saveRoute")
//	public ResponseEntity<?> saveRouteGeoLocation(@RequestBody PassengerGeoJson json) {
//		routeRepository.save(json);
//		return ResponseEntity.ok("Success");
//	}
//	
//	@PostMapping("/getSameRoute")
//	public ResponseEntity<?> getSameRoute(@RequestBody PassengerGeoJson json) {
//		List<PassengerGeoJson> passengerGeoJsonsList = routeRepository.findAll();
//		
//		List<String> ids = new ArrayList<String>();
//				
////		for(PassengerGeoJson passengerGeoJson : passengerGeoJsonsList) {
//////			List<ArrayList<Double>> coordinates = passengerGeoJson.getFeatures().get(0).getGeometry().getCoordinates();
//////			System.out.println(coordinates);
//////			for(Features feature : passengerGeoJson.getFeatures()) {
////////				List<ArrayList<Double>> coordinates = feature.getGeometry().getCoordinates();
//////				for(ArrayList<Double> coordinates : feature.getGeometry().getCoordinates()) {
////////					System.out.println(coordinates.get(0));
////////					System.out.println(coordinates.get(1));
////////					System.out.println("qqqqqqqqqqp");
//////					if (json.getFeatures().get(0).getGeometry().getCoordinates().size() == coordinates.size()) {
//////						System.out.println("count");
//////					}
//////				}
//////			}
////			
////			if (json.getFeatures().get(0).getGeometry().getCoordinates().size() >= passengerGeoJson.getFeatures().get(0).getGeometry().getCoordinates().size()) {
////				System.out.println(json.getFeatures().get(0).getGeometry().getCoordinates().size());
////			}
////			
////		}
//		
////		for (int i = 0; i < passengerGeoJsonsList.size(); i++) {
////			System.out.println(passengerGeoJsonsList.get(i).getFeatures().get(0).getGeometry().getCoordinates().size());
////		}
////		
////		return ResponseEntity.ok(passengerGeoJsonsList.get(0).getFeatures().get(0).getGeometry().getCoordinates().size());
//		
//		List<String> count = null;
//		
//		for(int i = 0; i<passengerGeoJsonsList.size(); i++) {
//			
//			count = new ArrayList<String>();
//			
//			if(json.getFeatures().get(0).getGeometry().getCoordinates().size()>=passengerGeoJsonsList.get(i).getFeatures().get(0).getGeometry().getCoordinates().size()) {
//				List<ArrayList<Double>> coordinates = passengerGeoJsonsList.get(i).getFeatures().get(0).getGeometry().getCoordinates();
//				List<ArrayList<Double>> coordinates2 = json.getFeatures().get(0).getGeometry().getCoordinates();
//				
//				for(int j= 0; j<coordinates.size(); j++) {
//					ArrayList<Double> arrayList = coordinates.get(j);
//					
//					boolean status = false;
//					
//					for(int k = 0; k< coordinates2.size(); k++) {
////						System.out.println(coordinates2.get(k).get(0) == arrayList.get(0));
//						if (coordinates2.get(k).get(0).equals(arrayList.get(0)) && coordinates2.get(k).get(1).equals(arrayList.get(1))) {
////							System.out.println(coordinates2.get(k).get(0));
//							status = true;
//						}
//						
//					}
////					System.out.println(count.size());
//					if(status == true) {
//						count.add("2");
//					}
//					
//				}
//				
//				if(count.size() == coordinates2.size())
//					System.out.println("count.size()");
//				ids.add(passengerGeoJsonsList.get(i).getId());
//			}
////			return ResponseEntity.ok(passengerGeoJsonsList.get(i).getId());
//		}
//		
//		
//		return ResponseEntity.ok(ids);
//	}
}
