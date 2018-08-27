package com.capgemini.domain;


import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.domain.MovieEntity.Builder;

@Entity
@Table(name = "MOVIES")
@EntityListeners(EntityListener.class)
public class MovieEntity extends AbstractEntity {
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String genre;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private Integer length;
	
	@Column(nullable = false)
	private LocalDate dateOfPremiere;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private Boolean threeD;
	
	@Column(nullable = false)
	private Integer budget;
	
	@Column(nullable = false)
	private Integer totalRevenue;
	
	@Column(nullable = false)
	private Integer firstWeekRevenue;
	
	@ManyToMany
	@JoinTable(name = "movie_actor", joinColumns = { @JoinColumn(name = "movie_id") },inverseJoinColumns = { @JoinColumn(name = "actor_id") })
	private List<ActorEntity> cast;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "studio_id")
	private StudioEntity studio;
	
	public MovieEntity() {
	}
	
	public MovieEntity(Builder builder) {
		super(builder.version,builder.id);
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

	public void setThreeD(Boolean threeD) {
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

	public List<ActorEntity> getCast() {
		return cast;
	}

	public void setCast(List<ActorEntity> cast) {
		this.cast = cast;
	}

	public StudioEntity getStudio() {
		return studio;
	}

	public void setStudio(StudioEntity studio) {
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
		private List<ActorEntity> cast;
		private StudioEntity studio;
		
		
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
		
		public Builder withCast (List<ActorEntity> cast){
			this.cast = cast;
			return this;
		}
		
		public Builder withStudio (StudioEntity studio){
			this.studio = studio;
			return this;
		}
		
		public MovieEntity build(){
			return new MovieEntity(this);
		}

	}
	
}
