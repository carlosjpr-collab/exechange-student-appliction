package fr.insa.soa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.dao.UniversityRepository;
import fr.insa.soa.entities.University;

@RestController
public class UniversityService {

	@Autowired
	UniversityRepository universityRepository;

	@GetMapping("/university")
	public Iterable<University> getInfoUniversity() {
		return universityRepository.findAll();
	}
}