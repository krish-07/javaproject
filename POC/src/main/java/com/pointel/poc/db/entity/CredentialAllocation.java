package com.pointel.poc.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CredentialAllocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CredentialAllocation_ID")
	private long id;

	@ManyToOne
	@JoinColumn(name = "Credential_ID")
	private APICredential apiCredential;

	@ManyToOne
	@JoinColumn(name = "User_ID")
	private Login login;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public APICredential getApiCredential() {
		return apiCredential;
	}

	public void setApiCredential(APICredential apiCredential) {
		this.apiCredential = apiCredential;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
