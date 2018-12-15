package fr.insa.soa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.dao.CourseRepository;
import fr.insa.soa.entities.Course;

@RestController
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	@GetMapping("/course")
	public Iterable<Course> getInfoUniversity() {
		return courseRepository.findAll();
	}
}