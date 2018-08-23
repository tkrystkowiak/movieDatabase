package com.capgemini.domain;

import java.sql.Date;

public class SearchCriteria {
	
	private String type;
	private String genre;
	private int minLength;
	private int maxLength;
	private Date minDateOfPremiere;
	private Date maxDateOfPremiere;
	private boolean is3D;
	private long studioId;
	
	public SearchCriteria(Builder builder) {
		this.type = builder.type;
		this.genre = builder.genre;
		this.minLength = builder.minLength;
		this.maxLength = builder.maxLength;
		this.minDateOfPremiere = builder.minDateOfPremiere;
		this.maxDateOfPremiere = builder.maxDateOfPremiere;
		this.is3D = builder.is3D;
		this.studioId = builder.studioId;
	}

	public static Builder newBuilder(){
		return new Builder();
	}

	public static class Builder{
		
		private String type;
		private String genre;
		private int minLength;
		private int maxLength;
		private Date minDateOfPremiere;
		private Date maxDateOfPremiere;
		private boolean is3D;
		private long studioId;
		
		public Builder withType(String type){
			this.type = type;
			return this;
		}
		
		public Builder withGenre(String genre){
			this.genre = genre;
			return this;
		}
		
		public Builder withMinLength(int minLegnth){
			this.minLength = minLegnth;
			return this;
		}
		
		public Builder withMaxLength(int maxLegnth){
			this.maxLength = maxLegnth;
			return this;
		}
		
		public Builder withMinDateOfPremiere(Date minDateOfPremiere){
			this.minDateOfPremiere = minDateOfPremiere;
			return this;
		}
		
		public Builder withMaxDateOfPremiere(Date maxDateOfPremiere){
			this.maxDateOfPremiere = maxDateOfPremiere;
			return this;
		}
		
		public Builder withIs3D(boolean is3D){
			this.is3D = is3D;
			return this;
		}
		
		public Builder withStudio(Long studioId){
			this.studioId = studioId;
			return this;
		}
		
		public SearchCriteria build(){
			return new SearchCriteria(this);
		}
		
		
	}
}
