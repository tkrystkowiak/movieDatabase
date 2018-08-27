package com.capgemini.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
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
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY, optional = false)
	private StudioEntity studio;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private ActorEntity actor;
	
	@Column(nullable  = false)
	private LocalDate effectiveDate;
	
	@Column
	private LocalDate expirationDate;

	public CooperationEntity(Builder builder) {
		super(builder.version, builder.id);
		this.actor = builder.actor;
		this.studio = builder.studio;
		this.effectiveDate = builder.effectiveDate;
		this.expirationDate = builder.expirationDate;
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
	
	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static class Builder{
		
		private Long version;

		private Long id;
		
		private StudioEntity studio;

		private ActorEntity actor;

		private LocalDate effectiveDate;

		private LocalDate expirationDate;
		
		public Builder withId (Long id){
			this.id = id;
			return this;
		}
		
		public Builder withVersion (Long version){
			this.version = version;
			return this;
		}
		
		public Builder withStudio (StudioEntity studio){
			this.studio = studio;
			return this;
		}
		
		public Builder withActor (ActorEntity actor){
			this.actor = actor;
			return this;
		}
		
		public Builder withEffectiveDate (LocalDate effectiveDate){
			this.effectiveDate = effectiveDate;
			return this;
		}
		
		public Builder withExpirationDate (LocalDate expirationDate){
			this.expirationDate = expirationDate;
			return this;
		}
		
		public CooperationEntity build(){
			return new CooperationEntity(this);
		}

	}
}
