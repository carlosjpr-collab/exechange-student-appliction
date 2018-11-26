package fr.insa.soa.ExchangeSemester.restServices;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.ExchangeSemester.dao.UserRepository;
import fr.insa.soa.ExchangeSemester.dao.UserStudentRepository;
import fr.insa.soa.ExchangeSemester.model.User;
import fr.insa.soa.ExchangeSemester.model.UserStudent;

@RestController
public class UserStudentRESTService {
	@Autowired
	UserStudentRepository userStudentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/service/userStudent")
	public List<UserStudent> getUserStudents() {
		return userStudentRepository.findAll();
	}
	
	@PutMapping("/service/userStudent")
	public String saveUserStudent(@RequestBody Map<String, String> json) {
		Optional<User> userOpt = userRepository.findById(Integer.parseInt(json.get("idUser")));
		User user = userOpt.get();
		
		UserStudent userStudent = new UserStudent();
		userStudent.setUser(user);
		userStudentRepository.save(userStudent);
		
		return "{\"success\": \"true\"}";
		
		//TODO: Catch exceptions and response false
	}
}
