package com.designprojectteam.easytravelling.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designprojectteam.easytravelling.helper.Coordinates;
//import com.designprojectteam.easytravelling.helper.Coordinates;
//import com.designprojectteam.easytravelling.helper.Features;
import com.designprojectteam.easytravelling.models.PassengerGeoJson;
import com.designprojectteam.easytravelling.models.RouteApiRequest;
import com.designprojectteam.easytravelling.repository.RouteRepository;
import com.designprojectteam.easytravelling.services.GMapRouteJsonToObject;

@RestController
@RequestMapping("/api/geo")
public class FriendFilterController {

	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	GMapRouteJsonToObject gMapRouteJsonToObject;

	@GetMapping("/allRoutes")
	public List<PassengerGeoJson> getAllRoutes() {

		List<PassengerGeoJson> findAll = routeRepository.findAll();

		return findAll;
	}

	@PostMapping("/saveRoute")
	public ResponseEntity<?> saveRouteGeoLocation(@RequestBody PassengerGeoJson json) {
		routeRepository.save(json);
		return ResponseEntity.ok("Success");
	}
	
	@PostMapping("/getSameRoute")
	public ResponseEntity<?> getSameRoute(@RequestBody PassengerGeoJson json) {
		List<PassengerGeoJson> passengerGeoJsonsList = routeRepository.findAll();
		
		List<String> ids = new ArrayList<String>();
				
//		for(PassengerGeoJson passengerGeoJson : passengerGeoJsonsList) {
////			List<ArrayList<Double>> coordinates = passengerGeoJson.getFeatures().get(0).getGeometry().getCoordinates();
////			System.out.println(coordinates);
////			for(Features feature : passengerGeoJson.getFeatures()) {
//////				List<ArrayList<Double>> coordinates = feature.getGeometry().getCoordinates();
////				for(ArrayList<Double> coordinates : feature.getGeometry().getCoordinates()) {
//////					System.out.println(coordinates.get(0));
//////					System.out.println(coordinates.get(1));
//////					System.out.println("qqqqqqqqqqp");
////					if (json.getFeatures().get(0).getGeometry().getCoordinates().size() == coordinates.size()) {
////						System.out.println("count");
////					}
////				}
////			}
//			
//			if (json.getFeatures().get(0).getGeometry().getCoordinates().size() >= passengerGeoJson.getFeatures().get(0).getGeometry().getCoordinates().size()) {
//				System.out.println(json.getFeatures().get(0).getGeometry().getCoordinates().size());
//			}
//			
//		}
		
//		for (int i = 0; i < passengerGeoJsonsList.size(); i++) {
//			System.out.println(passengerGeoJsonsList.get(i).getFeatures().get(0).getGeometry().getCoordinates().size());
//		}
//		
//		return ResponseEntity.ok(passengerGeoJsonsList.get(0).getFeatures().get(0).getGeometry().getCoordinates().size());
		
		List<String> count = null;
		
		for(int i = 0; i<passengerGeoJsonsList.size(); i++) {
			
			count = new ArrayList<String>();
			
			if(json.getFeatures().get(0).getGeometry().getCoordinates().size()>=passengerGeoJsonsList.get(i).getFeatures().get(0).getGeometry().getCoordinates().size()) {
				List<ArrayList<Double>> coordinates = passengerGeoJsonsList.get(i).getFeatures().get(0).getGeometry().getCoordinates();
				List<ArrayList<Double>> coordinates2 = json.getFeatures().get(0).getGeometry().getCoordinates();
				
				for(int j= 0; j<coordinates.size(); j++) {
					ArrayList<Double> arrayList = coordinates.get(j);
					
					boolean status = false;
					
					for(int k = 0; k< coordinates2.size(); k++) {
//						System.out.println(coordinates2.get(k).get(0) == arrayList.get(0));
						if (coordinates2.get(k).get(0).equals(arrayList.get(0)) && coordinates2.get(k).get(1).equals(arrayList.get(1))) {
//							System.out.println(coordinates2.get(k).get(0));
							status = true;
						}
						
					}
//					System.out.println(count.size());
					if(status == true) {
						count.add("2");
					}
					
				}
				
				if(count.size() == coordinates2.size())
					System.out.println("count.size()");
				ids.add(passengerGeoJsonsList.get(i).getId());
			}
//			return ResponseEntity.ok(passengerGeoJsonsList.get(i).getId());
		}
		
		
		return ResponseEntity.ok(ids);
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
}
