package fr.insa.soa.ExchangeSemester.restServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.ExchangeSemester.dao.UniversityRepository;
import fr.insa.soa.ExchangeSemester.model.University;

@RestController
public class UniversityService {
	
	@Autowired
	UniversityRepository univRepository;
	
	@GetMapping("/service/university")
	public List<University> getUniversities() {
		return univRepository.findAll();
	}
}
