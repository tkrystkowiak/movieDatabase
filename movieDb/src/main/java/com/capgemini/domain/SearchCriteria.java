package com.capgemini.domain;

import java.sql.Date;
import java.time.LocalDate;

public class SearchCriteria {
	
	private String type;
	private String genre;
	private Integer minLength;
	private Integer maxLength;
	private LocalDate minDateOfPremiere;
	private LocalDate maxDateOfPremiere;
	private Boolean threeD;
	private Long studioId;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public LocalDate getMinDateOfPremiere() {
		return minDateOfPremiere;
	}

	public void setMinDateOfPremiere(LocalDate minDateOfPremiere) {
		this.minDateOfPremiere = minDateOfPremiere;
	}

	public LocalDate getMaxDateOfPremiere() {
		return maxDateOfPremiere;
	}

	public void setMaxDateOfPremiere(LocalDate maxDateOfPremiere) {
		this.maxDateOfPremiere = maxDateOfPremiere;
	}

	public Boolean getThreeD() {
		return threeD;
	}

	public void setThreeD(boolean threeD) {
		this.threeD = threeD;
	}

	public Long getStudioId() {
		return studioId;
	}

	public void setStudioId(long studioId) {
		this.studioId = studioId;
	}

	public SearchCriteria(Builder builder) {
		this.type = builder.type;
		this.genre = builder.genre;
		this.minLength = builder.minLength;
		this.maxLength = builder.maxLength;
		this.minDateOfPremiere = builder.minDateOfPremiere;
		this.maxDateOfPremiere = builder.maxDateOfPremiere;
		this.threeD = builder.threeD;
		this.studioId = builder.studioId;
	}

	public static Builder newBuilder(){
		return new Builder();
	}

	public static class Builder{
		
		private String type;
		private String genre;
		private Integer minLength;
		private Integer maxLength;
		private LocalDate minDateOfPremiere;
		private LocalDate maxDateOfPremiere;
		private Boolean threeD;
		private Long studioId;
		
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
		
		public Builder withMinDateOfPremiere(LocalDate minDateOfPremiere){
			this.minDateOfPremiere = minDateOfPremiere;
			return this;
		}
		
		public Builder withMaxDateOfPremiere(LocalDate maxDateOfPremiere){
			this.maxDateOfPremiere = maxDateOfPremiere;
			return this;
		}
		
		public Builder withThreeD(boolean threeD){
			this.threeD = threeD;
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
