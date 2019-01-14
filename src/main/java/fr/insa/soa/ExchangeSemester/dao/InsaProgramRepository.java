package fr.insa.soa.ExchangeSemester.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.soa.ExchangeSemester.model.InsaProgram;

public interface InsaProgramRepository extends JpaRepository<InsaProgram, String> {

}
