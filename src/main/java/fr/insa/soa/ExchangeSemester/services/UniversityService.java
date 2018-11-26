package fr.insa.soa.ExchangeSemester.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.soa.ExchangeSemester.dao.UniversityRepository;
import fr.insa.soa.ExchangeSemester.dao.UserRepository;
import fr.insa.soa.ExchangeSemester.model.University;

@Service
public class UniversityService {

	private UniversityRepository universityRepository;

	@Autowired
	public UniversityService(UniversityRepository universityRepository) {
		this.universityRepository = universityRepository;
	}
	
	public boolean saveUniv(University university) {
		if(universityRepository.findByUrl(university.getUrl()) == null) {
			universityRepository.save(university);
			return true;
		}
		else {
			return false;
		}
	}
}
