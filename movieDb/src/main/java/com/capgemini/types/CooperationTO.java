package com.capgemini.types;

import java.time.LocalDate;

public class CooperationTO {
	
	private Long version;

	private Long id;
	
	private Long studio;

	private Long actor;

	private LocalDate effectiveDate;

	private LocalDate expirationDate;

	public CooperationTO(Builder builder) {
		this.version = builder.version;
		this.id = builder.id;
		this.studio = builder.studio;
		this.actor = builder.actor;
		this.effectiveDate = builder.effectiveDate;
		this.expirationDate = builder.expirationDate;
	}
	
	
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudio() {
		return studio;
	}

	public void setStudio(Long studio) {
		this.studio = studio;
	}

	public Long getActor() {
		return actor;
	}

	public void setActor(Long actor) {
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
		
		private Long studio;

		private Long actor;

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
		
		public Builder withStudio (Long studio){
			this.studio = studio;
			return this;
		}
		
		public Builder withActor (Long actor){
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
		
		public CooperationTO build(){
			return new CooperationTO(this);
		}

	}
	
}
