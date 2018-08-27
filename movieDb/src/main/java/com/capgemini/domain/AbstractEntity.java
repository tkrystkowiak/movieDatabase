package com.capgemini.domain;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Builder;
import lombok.experimental.SuperBuilder;;

@MappedSuperclass
public abstract class AbstractEntity {
	
	@Version 
	private Long version;
	
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column
	private Time persistTime;
	
	@Column
	private Time updateTime;
	
	public AbstractEntity() {
	}

	public AbstractEntity(Long version, Long id) {
		this.version = version;
		this.id = id;
	}

	public Time getPersistTime() {
		return persistTime;
	}

	public void setPersistTime(Time persistTime) {
		this.persistTime = persistTime;
	}

	public Time getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Time updateTime) {
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
}
