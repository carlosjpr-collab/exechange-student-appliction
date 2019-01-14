package fr.insa.soa.ExchangeSemester.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="notifications")
public class Notification implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_notifications")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	@JoinColumn(name = "id_user")
	private User user;
	
	@Column(name="date_notification")
	private Timestamp date;
	
	private String description;

	@Override
	public String toString() {
		return "Notif : " + this.description + this.date + this.user.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}