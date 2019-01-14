package fr.insa.soa.ExchangeSemester.restServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.ExchangeSemester.dao.NoteRepository;
import fr.insa.soa.ExchangeSemester.model.Note;

@RestController
public class NoteRESTService {
	@Autowired
	NoteRepository noteRepository;

	@GetMapping(value = "/service/note")
	public List<Note> getNotes() {
		return noteRepository.findAll();
	}
}
