package fr.insa.soa.ExchangeSemester.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uf")
public class Uf implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_UF")
	private String id;
	
	@Column(name="training_code")
	private String trainingCode;
	
	private String title;
	
	@Column(name="ECTS")
	private int ects;
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTrainingCode() {
		return trainingCode;
	}

	public void setTrainingCode(String trainingCode) {
		this.trainingCode = trainingCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEcts() {
		return ects;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
