package com.pointel.poc.db.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProjectAllocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProjectAllocate_ID")
	private long allocationId;

	@JsonIgnore
	@OneToMany(mappedBy = "projectAllocation")
	private List<AudioTable> audioTable;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Service_ID")
	private PocMenu pocMenu;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Project_ID")
	private Project project;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "User_ID")
	private Login login;

	public long getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(long allocationId) {
		this.allocationId = allocationId;
	}

	public List<AudioTable> getAudioTable() {
		return audioTable;
	}

	public void setAudioTable(List<AudioTable> audioTable) {
		this.audioTable = audioTable;
	}

	public PocMenu getPocMenu() {
		return pocMenu;
	}

	public void setPocMenu(PocMenu pocMenu) {
		this.pocMenu = pocMenu;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "ProjectAllocation [AllocationId=" + allocationId + ", audioTable=" + audioTable + ", pocMenu=" + pocMenu
				+ ", project=" + project + ", login=" + login + "]";
	}

}
