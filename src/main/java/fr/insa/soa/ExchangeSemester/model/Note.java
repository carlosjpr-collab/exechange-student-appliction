package fr.insa.soa.ExchangeSemester.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="notes")
public class Note implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private NotesPK id;
	
	private int note;

	public NotesPK getId() {
		return id;
	}

	public void setId(NotesPK id) {
		this.id = id;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}
	
	

}
