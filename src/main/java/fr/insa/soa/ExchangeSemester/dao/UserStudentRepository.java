package fr.insa.soa.ExchangeSemester.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.soa.ExchangeSemester.model.UserStudent;


public interface UserStudentRepository  extends JpaRepository<UserStudent, Integer> {

}
