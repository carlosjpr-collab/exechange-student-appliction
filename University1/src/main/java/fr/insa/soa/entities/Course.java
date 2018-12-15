package fr.insa.soa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	@Column(name = "ects_number")

	private String ectsNumber;

	private String description;

	@Column(name = "id_university")
	private Integer idUniversity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEctsNumber() {
		return ectsNumber;
	}

	public void setEctsNumber(String ectsNumber) {
		this.ectsNumber = ectsNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIdUniversity() {
		return idUniversity;
	}

	public void setIdUniversity(Integer idUniversity) {
		this.idUniversity = idUniversity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
