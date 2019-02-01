package com.pointel.poc.db.entity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AudioTable {

	@Transient
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-DD-yyyy HH:mm:SS");
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Audio_ID")
	private long audioId;

	@Column(name = "FileName", nullable = false)
	private String fileName;

	@Column(name = "AudioType", nullable = false)
	private String audioType;

	@Column(name = "Gender", unique = false, nullable = false)
	private String gender;

	@Column(name = "Language", nullable = false)
	private String language;

	@Column(name = "VoiceName", nullable = false)
	private String voiceName;

	@Column(name = "AudioInput", unique = true, nullable = false)
	private String audioInput;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedDateTime", nullable = false)
	private Date createdDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastModifiedDateTime", nullable = true)
	private Date lastModifiedDateTime;

	@Column(name = "AudioFilePath", nullable = false)
	private String audioFilePath;

	@Column(name = "AudioStatus", nullable = false)
	private String audioStatus;

	@Transient
	@Column(name = "AudioStream")
	private byte[] audioStream;

	@Transient
	private String projectName;

	@Transient
	private String hostName;

	@Transient
	private String aFPathConfigId;

	@Transient
	private String projectAllocateId;

	@Transient
	private String userId;

	@ManyToOne
	@JoinColumn(name = "ExtensionPath_ID")
	private AFPathConfig aFPathConfig;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ProjectAllocate_ID")
	private ProjectAllocation projectAllocation;

	public long getAudioId() {
		return audioId;
	}

	public void setAudioId(long audioId) {
		this.audioId = audioId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAudioType() {
		return audioType;
	}

	public void setAudioType(String audioType) {
		this.audioType = audioType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getVoiceName() {
		return voiceName;
	}

	public void setVoiceName(String voiceName) {
		this.voiceName = voiceName;
	}

	public String getAudioInput() {
		return audioInput;
	}

	public void setAudioInput(String audioInput) {
		this.audioInput = audioInput;
	}

	public String getCreatedDateTime() {
		if (createdDateTime != null) {
			return simpleDateFormat.format(createdDateTime);
		}
		return "-";
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getLastModifiedDateTime() {
		if (lastModifiedDateTime != null) {
			return simpleDateFormat.format(lastModifiedDateTime);
		}
		return "-";
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public String getAudioFilePath() {
		return audioFilePath;
	}

	public void setAudioFilePath(String audioFilePath) {
		this.audioFilePath = audioFilePath;
	}

	public String getAudioStatus() {
		return audioStatus;
	}

	public void setAudioStatus(String audioStatus) {
		this.audioStatus = audioStatus;
	}

	public byte[] getAudioStream() {
		return audioStream;
	}

	public void setAudioStream(byte[] audioStream) {
		this.audioStream = audioStream;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getaFPathConfigId() {
		return aFPathConfigId;
	}

	public void setaFPathConfigId(String aFPathConfigId) {
		this.aFPathConfigId = aFPathConfigId;
	}

	public String getProjectAllocateId() {
		return projectAllocateId;
	}

	public void setProjectAllocateId(String projectAllocateId) {
		this.projectAllocateId = projectAllocateId;
	}

	public AFPathConfig getaFPathConfig() {
		return aFPathConfig;
	}

	public void setaFPathConfig(AFPathConfig aFPathConfig) {
		this.aFPathConfig = aFPathConfig;
	}

	public ProjectAllocation getProjectAllocation() {
		return projectAllocation;
	}

	public void setProjectAllocation(ProjectAllocation projectAllocation) {
		this.projectAllocation = projectAllocation;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AudioTable [simpleDateFormat=" + simpleDateFormat + ", audioId=" + audioId + ", fileName=" + fileName
				+ ", audioType=" + audioType + ", gender=" + gender + ", language=" + language + ", voiceName="
				+ voiceName + ", audioInput=" + audioInput + ", createdDateTime=" + createdDateTime
				+ ", lastModifiedDateTime=" + lastModifiedDateTime + ", audioFilePath=" + audioFilePath
				+ ", audioStatus=" + audioStatus + ", audioStream=" + Arrays.toString(audioStream) + ", projectName="
				+ projectName + ", hostName=" + hostName + ", aFPathConfigId=" + aFPathConfigId + ", projectAllocateId="
				+ projectAllocateId + ", userId=" + userId + ", aFPathConfig=" + aFPathConfig + ", projectAllocation="
				+ projectAllocation + "]";
	}

}
