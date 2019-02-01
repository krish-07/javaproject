package com.pointel.poc.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AudioExtension {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Extension_ID")
	private long id;

	@Column(name = "ExtensionType", nullable = false)
	private String extensionType;

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

	@Override
	public String toString() {
		return "AudioExtension [id=" + id + ", extensionType=" + extensionType + "]";
	}

}
