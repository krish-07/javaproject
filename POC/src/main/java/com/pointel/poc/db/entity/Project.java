package com.pointel.poc.db.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Project_ID")
	private long projectId;

	@Column(name = "HostName", nullable = false)
	private String hostName;

	@Column(name = "ProjectName", nullable = false)
	private String projectName;

	@Column(name = "ProjectPath", unique = false, nullable = false)
	private String projectPath;

	@OneToMany(mappedBy = "project")
	private List<ProjectAllocation> projectAllocation;
	@JsonIgnore
	@OneToMany(mappedBy = "project")
	private List<AFPathConfig> afPathConfig;

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public List<ProjectAllocation> getProjectAllocation() {
		return projectAllocation;
	}

	public void setProjectAllocation(List<ProjectAllocation> projectAllocation) {
		this.projectAllocation = projectAllocation;
	}

	public List<AFPathConfig> getAfPathConfig() {
		return afPathConfig;
	}

	public void setAfPathConfig(List<AFPathConfig> afPathConfig) {
		this.afPathConfig = afPathConfig;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", hostName=" + hostName + ", projectName=" + projectName
				+ ", projectPath=" + projectPath + ", projectAllocation=" + projectAllocation + ", afPathConfig="
				+ afPathConfig + "]";
	}

}
