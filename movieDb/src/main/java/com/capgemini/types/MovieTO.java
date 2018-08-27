package com.capgemini.types;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.StudioEntity;

import lombok.Builder;
import lombok.NonNull;

public class MovieTO {
	
	private Long version;
	private Long id;
	private String title;
	private String genre;
	private String type;
	private Integer length;
	private LocalDate dateOfPremiere;
	private String country;
	private Boolean threeD;
	private Integer budget;
	private Integer totalRevenue;
	private Integer firstWeekRevenue;
	private List<Long> cast;
	private Long studio;

	
	
	public MovieTO(Builder builder) {
		this.version = builder.version;
		this.id = builder.id; 
		this.title = builder.title;
		this.genre = builder.genre;
		this.type = builder.type;
		this.length = builder.length;
		this.dateOfPremiere = builder.dateOfPremiere;
		this.country = builder.country;
		this.threeD = builder.threeD;
		this.budget = builder.budget;
		this.totalRevenue = builder.totalRevenue;
		this.firstWeekRevenue = builder.firstWeekRevenue;
		this.cast = builder.cast;
		this.studio = builder.studio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public LocalDate getDateOfPremiere() {
		return dateOfPremiere;
	}

	public void setDateOfPremiere(LocalDate dateOfPremiere) {
		this.dateOfPremiere = dateOfPremiere;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getThreeD() {
		return threeD;
	}

	public void setIs3D(Boolean threeD) {
		this.threeD = threeD;
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	public Integer getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Integer totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Integer getFirstWeekRevenue() {
		return firstWeekRevenue;
	}

	public void setFirstWeekRevenue(Integer firstWeekRevenue) {
		this.firstWeekRevenue = firstWeekRevenue;
	}

	public List<Long> getCast() {
		return cast;
	}

	public void setCast(List<Long> cast) {
		this.cast = cast;
	}

	public Long getStudio() {
		return studio;
	}

	public void setStudio(Long studio) {
		this.studio = studio;
	}
	
	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static class Builder{
		
		private Long id;
		private Long version;
		private String title;
		private String genre;
		private String type;
		private Integer length;
		private LocalDate dateOfPremiere;
		private String country;
		private Boolean threeD;
		private Integer budget;
		private Integer totalRevenue;
		private Integer firstWeekRevenue;
		private List<Long> cast;
		private Long studio;
		
		
		public Builder withId (Long id){
			this.id = id;
			return this;
		}
		
		public Builder withVersion (Long version){
			this.version = version;
			return this;
		}
		
		public Builder withTitle (String title){
			this.title = title;
			return this;
		}
		
		public Builder withGenre (String genre){
			this.genre = genre;
			return this;
		}
		
		public Builder withType (String type){
			this.type = type;
			return this;
		}
		
		public Builder withLength (Integer length){
			this.length = length;
			return this;
		}
		
		public Builder withDateOfPremiere (LocalDate dateOfPremiere){
			this.dateOfPremiere = dateOfPremiere;
			return this;
		}
		
		public Builder withCountry (String country){
			this.country = country;
			return this;
		}
		
		public Builder withThreeD (Boolean threeD){
			this.threeD = threeD;
			return this;
		}
		
		public Builder withBudget (Integer budget){
			this.budget = budget;
			return this;
		}
		
		public Builder withTotalRevenue (Integer totalRevenue){
			this.totalRevenue = totalRevenue;
			return this;
		}
		
		public Builder withFirstWeekRevenue (Integer firstWeekRevenue){
			this.firstWeekRevenue = firstWeekRevenue;
			return this;
		}
		
		public Builder withCast (List<Long> cast){
			this.cast = cast;
			return this;
		}
		
		public Builder withStudio (Long studio){
			this.studio = studio;
			return this;
		}
		
		public MovieTO build(){
			return new MovieTO(this);
		}
		
	}
	
}
