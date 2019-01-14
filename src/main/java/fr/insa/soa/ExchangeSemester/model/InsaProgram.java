package fr.insa.soa.ExchangeSemester.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="insa_program")
public class InsaProgram implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="training_code")
	private String trainingCode;
	
	private String title;
	
	private String department;

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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
