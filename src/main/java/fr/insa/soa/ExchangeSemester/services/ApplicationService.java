package fr.insa.soa.ExchangeSemester.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.soa.ExchangeSemester.dao.ApplicationRepository;
import fr.insa.soa.ExchangeSemester.model.Application;

@Service
public class ApplicationService {
	
	private ApplicationRepository applicationRepository;
	
	@Autowired
	public ApplicationService(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}
	
	public boolean saveApplication(Application application) {
		List<Application> listApp = applicationRepository.findAllByStudent(application.getStudent());
		for(Application app : listApp) {
			if(app.getUniversity() == application.getUniversity()) {
				return false;		//if the user has already applied to this university
			}
		}
		
		applicationRepository.save(application);
		return true;
	}
}
