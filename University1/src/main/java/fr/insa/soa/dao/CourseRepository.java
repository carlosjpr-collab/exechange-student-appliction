package fr.insa.soa.dao;

import org.springframework.data.repository.CrudRepository;
import fr.insa.soa.entities.Course;

public interface CourseRepository  extends CrudRepository<Course, Integer> {

}