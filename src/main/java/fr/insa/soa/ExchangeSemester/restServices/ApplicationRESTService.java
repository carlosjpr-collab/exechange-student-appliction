package fr.insa.soa.ExchangeSemester.restServices;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.ExchangeSemester.dao.ApplicationRepository;
import fr.insa.soa.ExchangeSemester.dao.NotificationRepository;
import fr.insa.soa.ExchangeSemester.dao.UniversityRepository;
import fr.insa.soa.ExchangeSemester.dao.UserRepository;
import fr.insa.soa.ExchangeSemester.dao.UserStudentRepository;
import fr.insa.soa.ExchangeSemester.dao.UserUniversityRepository;
import fr.insa.soa.ExchangeSemester.model.Application;
import fr.insa.soa.ExchangeSemester.model.Notification;
import fr.insa.soa.ExchangeSemester.model.University;
import fr.insa.soa.ExchangeSemester.model.User;
import fr.insa.soa.ExchangeSemester.model.UserStudent;
import fr.insa.soa.ExchangeSemester.model.UserUniversity;
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
	private UserUniversityRepository userUniversityRepository;

	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;

	@GetMapping(value = "/service/application", produces = "application/json")
	public List<Application> getApplications() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Application> listApplication = null;
		
		String login = auth.getName(); // get the username
		User user = userRepository.findByLogin(login); // not found exception
		int idUser = user.getId();

		if (auth.getAuthorities().toString().equals("[ROLE_STUDENT]")) {
			Optional<UserStudent> optStudent = userStudentRepository.findById(user.getId());
			UserStudent student = null;
			if (optStudent.isPresent()) {
				student = optStudent.get();
				System.out.println(student.toString());
			} else {
				System.out.println("no user ?");
			}

			 listApplication = applicationRepository.findAllByStudent(student);   //all the applications for the specified student
			 		}
		else if(auth.getAuthorities().toString().equals("[ROLE_UNIVERSITY]")) {
			Optional<UserUniversity> optUserUniversity = userUniversityRepository.findById(user.getId());
			//TODO: OPTIONAL TO USERUNIV
			
			University univ = optUserUniversity.get().getUniversity();
			listApplication = applicationRepository.findAllByUniversity(univ);  //all the applications for the specified university
		}
		else if(auth.getAuthorities().toString().equals("[ROLE_INSA]")) {
			listApplication = applicationRepository.findAll(); //INSA has to see all the applications
		}

		return listApplication;
	}

	@PutMapping(value = "/service/application", produces = "application/json")
	public String putApplication(@RequestBody Map<String, String> json) {
		ApplicationService applicationService = new ApplicationService(applicationRepository);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName(); // get the username
		User user = userRepository.findByLogin(login); // not found exception

		int idUser = user.getId();
		int idUniv = Integer.parseInt(json.get("idUniv"));
		int agreement = Integer.parseInt(json.get("agreement"));
		String status = json.get("status");

		Optional<UserStudent> optStudent = userStudentRepository.findById(idUser);
		UserStudent student = null;
		if (optStudent.isPresent()) {
			student = optStudent.get();
			System.out.println(student.toString());
		} else {
			System.out.println("no user ?");
		}

		Optional<University> university = univRepository.findById(idUniv);

		Application application = new Application();
		application.setStudent(student);
		application.setUniversity(university.get()); // see if the university is present ??
		application.setAgreement(agreement);
		application.setStatus(status);

		if (applicationService.saveApplication(application) == true) {
			return "{\"success\": \"true\"}";
		} else {
			return "{\"success\": \"false\"}";
		}
	}
	
	@PostMapping("/service/application")
	public void acceptRefuseApplication(@RequestBody Map<String, String> json) {
		if(json.get("type").toString().equals("response")) {
			int idApplication = Integer.parseInt(json.get("idApplication"));
			String response = json.get("response").toString();
			String login = json.get("idUser"); // get the username

			Optional<Application> appOpt = applicationRepository.findById(idApplication);
			Application app = appOpt.get();
			String type = "";
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			User user = userRepository.findByLogin(login); // not found exception
			
			if(auth.getAuthorities().toString().equals("[ROLE_UNIVERSITY]")){
				type = "university";
			}
			else {
				type = "INSA";
			}
			
			Notification notif = new Notification();
			notif.setUser(user);
			
			
			if(response.equals("OK")) {
				app.setStatus("accepted by "+ type);
				notif.setDescription("Your application has been accepted by "+ type);
			}
			else {
				app.setStatus("refused by "+ type);
				notif.setDescription("Your application has been refused by "+ type);
			}
			
			notificationRepository.save(notif);
			applicationRepository.save(app);
		}
	}

	// TODO: delete
}
