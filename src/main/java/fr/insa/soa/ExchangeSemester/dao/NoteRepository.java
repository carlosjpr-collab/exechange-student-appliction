package fr.insa.soa.ExchangeSemester.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.soa.ExchangeSemester.model.Note;
import fr.insa.soa.ExchangeSemester.model.NotesPK;

public interface NoteRepository extends JpaRepository<Note, NotesPK> {

}
