package fr.insa.soa.ExchangeSemester.restServices;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.ExchangeSemester.dao.UniversityRepository;
import fr.insa.soa.ExchangeSemester.dao.UserRepository;
import fr.insa.soa.ExchangeSemester.dao.UserUniversityRepository;
import fr.insa.soa.ExchangeSemester.model.University;
import fr.insa.soa.ExchangeSemester.model.User;
import fr.insa.soa.ExchangeSemester.model.UserUniversity;

@RestController
public class UserUniversityRESTService {
	
	@Autowired
	private UserUniversityRepository userUnivRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UniversityRepository univRepository;
	
	@GetMapping("/service/userUniversity")
	public List<UserUniversity> getUniversities() {
		return userUnivRepository.findAll();
	}
	
	@PutMapping("/service/userUniversity")
	public String saveUserUniv(@RequestBody Map<String, String> json) {
		Optional<User> userOpt = userRepository.findById(Integer.parseInt(json.get("idUser")));
		User user = userOpt.get();
		Optional<University> univOpt = univRepository.findById(Integer.parseInt(json.get("idUniv")));
		University univ = univOpt.get();
		
		UserUniversity userUniv = new UserUniversity();
		userUniv.setUser(user);
		userUniv.setUniversity(univ);
		userUnivRepository.save(userUniv);
		
		//catch SQL Exceptions	
		
		return "{\"success\": \"true\"}";
	}
}
