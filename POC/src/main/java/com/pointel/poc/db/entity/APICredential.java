package com.pointel.poc.db.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class APICredential {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Credential_ID")
	private long credentialID;

	@Column(name = "GoogleJson", nullable = false)
	private String googleJson;

	@Column(name = "AmazonAccessKey", nullable = false)
	private String amazonAccessKey;

	@Column(name = "AmazonSecretKey", unique = false, nullable = false)
	private String amazonSecretKey;

	@Column(name = "AmazonRegion", nullable = false)
	private String amazonRegion;

	@ManyToOne
	@JoinColumn(name = "Service_ID")
	private PocMenu pocMenu;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "apiCredential")
	private List<CredentialAllocation> credentialAllocations;

	public long getCredentialID() {
		return credentialID;
	}

	public void setCredentialID(long credentialID) {
		this.credentialID = credentialID;
	}

	public String getGoogleJson() {
		return googleJson;
	}

	public void setGoogleJson(String googleJson) {
		this.googleJson = googleJson;
	}

	public String getAmazonAccessKey() {
		return amazonAccessKey;
	}

	public void setAmazonAccessKey(String amazonAccessKey) {
		this.amazonAccessKey = amazonAccessKey;
	}

	public String getAmazonSecretKey() {
		return amazonSecretKey;
	}

	public void setAmazonSecretKey(String amazonSecretKey) {
		this.amazonSecretKey = amazonSecretKey;
	}

	public String getAmazonRegion() {
		return amazonRegion;
	}

	public void setAmazonRegion(String amazonRegion) {
		this.amazonRegion = amazonRegion;
	}

	public PocMenu getPocMenu() {
		return pocMenu;
	}

	public void setPocMenu(PocMenu pocMenu) {
		this.pocMenu = pocMenu;
	}

	@Override
	public String toString() {
		return "APICredential [credentialID=" + credentialID + ", googleJson=" + googleJson + ", amazonAccessKey="
				+ amazonAccessKey + ", amazonSecretKey=" + amazonSecretKey + ", amazonRegion=" + amazonRegion
				+ ", pocMenu=" + pocMenu + ", credentialAllocations=" + credentialAllocations + "]";
	}

}
