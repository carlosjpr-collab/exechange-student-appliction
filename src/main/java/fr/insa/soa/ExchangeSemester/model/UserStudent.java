package fr.insa.soa.ExchangeSemester.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class UserStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId()
	@JoinColumn(name = "id_student")
	private User user;

	@Column(name = "INSA_ranking")
	private Integer insaRanking;

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

	public Integer getInsaRanking() {
		return insaRanking;
	}

	public void setInsaRanking(Integer insaRanking) {
		this.insaRanking = insaRanking;
	}

}
