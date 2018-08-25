package com.capgemini.domain;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COOPERATIONS")
@EntityListeners(EntityListener.class)
public class CooperationEntity extends AbstractEntity {
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private StudioEntity studio;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private ActorEntity actor;
	
	@Column(nullable  = false)
	private LocalDate effectiveDate;
	
	@Column
	private LocalDate expirationDate;

	public CooperationEntity(StudioEntity studio, ActorEntity actor, LocalDate effectiveDate, LocalDate expirationDate) {
		this.studio = studio;
		this.actor = actor;
		this.effectiveDate = effectiveDate;
		this.expirationDate = expirationDate;
	}

	public CooperationEntity(StudioEntity studio, ActorEntity actor, LocalDate effectiveDate) {
		this.studio = studio;
		this.actor = actor;
		this.effectiveDate = effectiveDate;
	}
	
	public CooperationEntity() {}

	public StudioEntity getStudio() {
		return studio;
	}

	public void setStudio(StudioEntity studio) {
		this.studio = studio;
	}

	public ActorEntity getActor() {
		return actor;
	}

	public void setActor(ActorEntity actor) {
		this.actor = actor;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
}
