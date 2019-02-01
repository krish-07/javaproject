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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AFPathConfig {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ExtensionPath_ID")
	private long id;

	@Column(name = "ExtensionPath", nullable = false)
	private String extensionType;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aFPathConfig")
	private List<AudioTable> audioTable;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Project_ID")
	private Project project;

	@Transient
	private String projectId;

	@Transient
	private String extension;

	@ManyToOne
	@JoinColumn(name = "Extension_ID")
	private AudioExtension audioExtension;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExtensionType() {
		return extensionType;
	}

	public void setExtensionType(String extensionType) {
		this.extensionType = extensionType;
	}

	public List<AudioTable> getAudioTable() {
		return audioTable;
	}

	public void setAudioTable(List<AudioTable> audioTable) {
		this.audioTable = audioTable;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public AudioExtension getAudioExtension() {
		return audioExtension;
	}

	public void setAudioExtension(AudioExtension audioExtension) {
		this.audioExtension = audioExtension;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		return "AFPathConfig [id=" + id + ", extensionType=" + extensionType + ", audioTable=" + audioTable
				+ ", project=" + project + ", projectId=" + projectId + ", extension=" + extension + ", audioExtension="
				+ audioExtension + "]";
	}
}
