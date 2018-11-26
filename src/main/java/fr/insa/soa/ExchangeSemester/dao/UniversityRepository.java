package fr.insa.soa.ExchangeSemester.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.soa.ExchangeSemester.model.University;

public interface UniversityRepository extends JpaRepository<University, Integer> {
	University findByUrl(String url);
}
