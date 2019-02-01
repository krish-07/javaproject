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
public class PocMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Service_ID")
	private Long serviceId;

	@Column(name = "ApiServiceName", nullable = false)
	private String apiServiceName;

	@Column(name = "Language_Menus", nullable = false)
	private String languageMenu;

	@Column(name = "VoiceName_Menus", nullable = false)
	private String voiceNameMenu;

	@Column(name = "Gender_Menus", nullable = false)
	private String genderMenu;

	@Column(name = "AudioType_Menus", nullable = false)
	private String audiotypeMenu;
	@JsonIgnore
	@OneToMany(mappedBy = "pocMenu")
	private List<APICredential> apiCredential;
	@JsonIgnore
	@OneToMany(mappedBy = "pocMenu")
	private List<VoiceConfig> voiceConfig;
	@JsonIgnore
	@OneToMany(mappedBy = "pocMenu")
	private List<ProjectAllocation> projectAllocation;

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getApiServiceName() {
		return apiServiceName;
	}

	public void setApiServiceName(String apiServiceName) {
		this.apiServiceName = apiServiceName;
	}

	public String getLanguageMenu() {
		return languageMenu;
	}

	public void setLanguageMenu(String languageMenu) {
		this.languageMenu = languageMenu;
	}

	public String getVoiceNameMenu() {
		return voiceNameMenu;
	}

	public void setVoiceNameMenu(String voiceNameMenu) {
		this.voiceNameMenu = voiceNameMenu;
	}

	public String getGenderMenu() {
		return genderMenu;
	}

	public void setGenderMenu(String genderMenu) {
		this.genderMenu = genderMenu;
	}

	public String getAudiotypeMenu() {
		return audiotypeMenu;
	}

	public void setAudiotypeMenu(String audiotypeMenu) {
		this.audiotypeMenu = audiotypeMenu;
	}

	public List<APICredential> getApiCredential() {
		return apiCredential;
	}

	public void setApiCredential(List<APICredential> apiCredential) {
		this.apiCredential = apiCredential;
	}

	public List<VoiceConfig> getVoiceConfig() {
		return voiceConfig;
	}

	public void setVoiceConfig(List<VoiceConfig> voiceConfig) {
		this.voiceConfig = voiceConfig;
	}

	public List<ProjectAllocation> getProject() {
		return projectAllocation;
	}

	public void setProject(List<ProjectAllocation> projectAllocation) {
		this.projectAllocation = projectAllocation;
	}

	@Override
	public String toString() {
		return "PocMenu [serviceId=" + serviceId + ", apiServiceName=" + apiServiceName + ", languageMenu="
				+ languageMenu + ", voiceNameMenu=" + voiceNameMenu + ", genderMenu=" + genderMenu + ", audiotypeMenu="
				+ audiotypeMenu + ", apiCredential=" + apiCredential + ", voiceConfig=" + voiceConfig
				+ ", projectAllocation=" + projectAllocation + "]";
	}

}
