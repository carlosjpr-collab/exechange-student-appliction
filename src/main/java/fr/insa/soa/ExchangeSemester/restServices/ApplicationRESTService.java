package fr.insa.soa.ExchangeSemester.restServices;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.ExchangeSemester.dao.ApplicationRepository;
import fr.insa.soa.ExchangeSemester.dao.UniversityRepository;
import fr.insa.soa.ExchangeSemester.dao.UserRepository;
import fr.insa.soa.ExchangeSemester.dao.UserStudentRepository;
import fr.insa.soa.ExchangeSemester.model.Application;
import fr.insa.soa.ExchangeSemester.model.University;
import fr.insa.soa.ExchangeSemester.model.User;
import fr.insa.soa.ExchangeSemester.model.UserStudent;
import fr.insa.soa.ExchangeSemester.services.ApplicationService;

@RestController
public class ApplicationRESTService {
	@Autowired
	UniversityRepository univRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserStudentRepository userStudentRepository;
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@GetMapping(value = "/service/application", produces = "application/json")
	public List<Application> getApplications() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();			//get the username
		User user = userRepository.findByLogin(login);			//not found exception
		
		int idUser = user.getId();
		
		Optional<UserStudent> optStudent = userStudentRepository.findById(user.getId());	
		UserStudent student = null;
		if(optStudent.isPresent()) {
			student = optStudent.get();
			System.out.println(student.toString());
		}
		else {
			System.out.println("no user ?");
		}	
		
		List<Application> listApplication = applicationRepository.findAllByStudent(student);
		
		return listApplication;
	}
	
	@PutMapping(value = "/service/application", produces = "application/json")
	public String putApplication(@RequestBody Map<String, String> json) {
		ApplicationService applicationService = new ApplicationService(applicationRepository);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();			//get the username
		User user = userRepository.findByLogin(login);			//not found exception
		
		int idUser = user.getId();
		int idUniv = Integer.parseInt(json.get("idUniv"));
		int agreement = Integer.parseInt(json.get("agreement"));
		String status = json.get("status");
		
		Optional<UserStudent> optStudent = userStudentRepository.findById(idUser);	
		UserStudent student = null;
		if(optStudent.isPresent()) {
			student = optStudent.get();
			System.out.println(student.toString());
		}
		else {
			System.out.println("no user ?");
		}	
		
		
		Optional<University> university = univRepository.findById(idUniv);
		
		Application application = new Application();
		application.setStudent(student);
		application.setUniversity(university.get());		//see if the university is present ??
		application.setAgreement(agreement);
		application.setStatus(status);
		
		if(applicationService.saveApplication(application) == true) {
			return "{\"success\": \"true\"}";
		}
		else {
			return "{\"success\": \"false\"}";
		}
	}
	
	//TODO: delete
}
