package com.pointel.poc.db.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_ID")
	private long userId;

	@Column(name = "UserName", nullable = false)
	private String userName;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "Role", nullable = false)
	private String role;

	@Column(name = "Active", nullable = false)
	private String active;

	@Column(name = "Comment", nullable = true)
	private String comment;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "login")
	private List<ProjectAllocation> group;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "login")
	private List<CredentialAllocation> credentialAllocations;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<ProjectAllocation> getGroup() {
		return group;
	}

	public void setGroup(List<ProjectAllocation> group) {
		this.group = group;
	}

	public List<CredentialAllocation> getCredentialAllocations() {
		return credentialAllocations;
	}

	public void setCredentialAllocations(List<CredentialAllocation> credentialAllocations) {
		this.credentialAllocations = credentialAllocations;
	}

	@Override
	public String toString() {
		return "Login [userId=" + userId + ", userName=" + userName + ", password=" + password + ", role=" + role
				+ ", active=" + active + ", comment=" + comment + ", group=" + group + ", credentialAllocations="
				+ credentialAllocations + "]";
	}

}
