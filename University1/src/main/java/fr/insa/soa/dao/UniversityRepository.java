package fr.insa.soa.dao;

import org.springframework.data.repository.CrudRepository;

import fr.insa.soa.entities.University;

public interface UniversityRepository extends CrudRepository<University, Integer> {

}