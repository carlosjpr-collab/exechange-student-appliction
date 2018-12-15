package fr.insa.soa.ExchangeSemester.restServices;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.ExchangeSemester.dao.UniversityRepository;

@RestController
public class CourseRESTService {

	@Autowired
	UniversityRepository univRepository;
	
	@GetMapping(value="/service/course" , produces = "application/json")
	public String getCoursesUniv(@RequestParam("id") int id) {
		Client client = ClientBuilder.newClient();
		String url = univRepository.findById(id).get().getUrl();
		Response response = client.target(url+"/course").request().get();
		
		return response.readEntity(String.class);
	}
}