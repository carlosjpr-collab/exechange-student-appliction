package fr.insa.soa.ExchangeSemester.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class NotesPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	@JoinColumn(name = "id_UF")
	private Uf uf;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	@JoinColumn(name = "id_student")
	private UserStudent student;

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public UserStudent getStudent() {
		return student;
	}

	public void setStudent(UserStudent student) {
		this.student = student;
	}

}
