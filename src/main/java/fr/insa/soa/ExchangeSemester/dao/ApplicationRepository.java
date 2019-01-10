package fr.insa.soa.ExchangeSemester.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.soa.ExchangeSemester.model.Application;
import fr.insa.soa.ExchangeSemester.model.University;
import fr.insa.soa.ExchangeSemester.model.UserStudent;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	List<Application> findAllByStudent(UserStudent student);
	List<Application> findAllByUniversity(University university);
}