package fr.insa.soa.ExchangeSemester.restServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.ExchangeSemester.dao.UniversityRepository;
import fr.insa.soa.ExchangeSemester.model.University;
import fr.insa.soa.ExchangeSemester.services.UniversityService;

@RestController
public class UniversityRESTService {

	@Autowired
	UniversityRepository univRepository;

	@GetMapping("/service/university")
	public List<University> getUniversities() {
		return univRepository.findAll();
	}

	@PutMapping("/service/university")
	public String saveUniversity(@RequestBody University univ) {

		UniversityService univService = new UniversityService(univRepository);

		boolean success = univService.saveUniv(univ);

		if (success) {
			return "{" + "\"success\": \"true\"," + "\"id\": \"" + univ.getId() + "\"}";
		} else {
			return "{\"success\": \"false\"}";
		}
	}
}