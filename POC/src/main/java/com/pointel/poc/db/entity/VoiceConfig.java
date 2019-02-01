package com.pointel.poc.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VoiceConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Voice_ID")
	private long voiceId;

	@Column(name = "Language_Config", nullable = false)
	private String languageConfig;

	@Column(name = "VoiceType_Config", nullable = false)
	private String voiceTypeConfig;

	@Column(name = "Gender_Config", nullable = false)
	private String genderConfig;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Service_ID")
	private PocMenu pocMenu;

	public long getVoiceId() {
		return voiceId;
	}

	public void setVoiceId(long voiceId) {
		this.voiceId = voiceId;
	}

	public String getLanguageConfig() {
		return languageConfig;
	}

	public void setLanguageConfig(String languageConfig) {
		this.languageConfig = languageConfig;
	}

	public String getVoiceTypeConfig() {
		return voiceTypeConfig;
	}

	public void setVoiceTypeConfig(String voiceTypeConfig) {
		this.voiceTypeConfig = voiceTypeConfig;
	}

	public String getGenderConfig() {
		return genderConfig;
	}

	public void setGenderConfig(String genderConfig) {
		this.genderConfig = genderConfig;
	}

	public PocMenu getPocMenu() {
		return pocMenu;
	}

	public void setPocMenu(PocMenu pocMenu) {
		this.pocMenu = pocMenu;
	}
}
