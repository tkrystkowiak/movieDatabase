package com.capgemini.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.domain.ActorEntity.Builder;

import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "MOVIES")
@EntityListeners(EntityListener.class)
@SuperBuilder
public class MovieEntity extends AbstractEntity {
	
	@NonNull
	@Column(nullable = false)
	private String title;
	
	@NonNull
	@Column(nullable = false)
	private String genre;
	
	@NonNull
	@Column(nullable = false)
	private String type;
	
	@NonNull
	@Column(nullable = false)
	private Integer length;
	
	@NonNull
	@Column(nullable = false)
	private Date dateOfPremiere;
	
	@NonNull
	@Column(nullable = false)
	private String country;
	
	@NonNull
	@Column(nullable = false)
	private Boolean is3D;
	
	@NonNull
	@Column(nullable = false)
	private Integer budget;
	
	@NonNull
	@Column(nullable = false)
	private Integer totalRevenue;
	
	@NonNull
	@Column(nullable = false)
	private Integer firstWeekRevenue;
	
	@ManyToMany
	@JoinTable(name = "movie_actor", joinColumns = { @JoinColumn(name = "movie_id") },inverseJoinColumns = { @JoinColumn(name = "actor_id") })
	private List<ActorEntity> cast;
	
	@ManyToOne
	@JoinColumn(name = "studio_id")
	private StudioEntity studio;
	
	public MovieEntity() {
	}
	
	public MovieEntity(Builder builder) {
		super(builder.version,builder.id, builder.persistTime, builder.updateTime);
		this.title = builder.title;
		this.genre = builder.genre;
		this.type = builder.type;
		this.length = builder.length;
		this.dateOfPremiere = builder.dateOfPremiere;
		this.country = builder.country;
		this.is3D = builder.is3D;
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

	public Date getDateOfPremiere() {
		return dateOfPremiere;
	}

	public void setDateOfPremiere(Date dateOfPremiere) {
		this.dateOfPremiere = dateOfPremiere;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getIs3D() {
		return is3D;
	}

	public void setIs3D(Boolean is3d) {
		is3D = is3d;
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
		private Time persistTime;
		private Time updateTime;
		private Long version;
		private String title;
		private String genre;
		private String type;
		private Integer length;
		private Date dateOfPremiere;
		private String country;
		private Boolean is3D;
		private Integer budget;
		private Integer totalRevenue;
		private Integer firstWeekRevenue;
		private List<ActorEntity> cast;
		private StudioEntity studio;
		
		
		public Builder withId (Long id){
			this.id = id;
			return this;
		}
		
		public Builder withPersistTime (Time persistTime){
			this.persistTime = persistTime;
			return this;
		}
		
		public Builder withUpdateTime (Time updateTime){
			this.updateTime = updateTime;
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
		
		public Builder withDateOfPremiere (Date dateOfPremiere){
			this.dateOfPremiere = dateOfPremiere;
			return this;
		}
		
		public Builder withCountry (String country){
			this.country = country;
			return this;
		}
		
		public Builder withIs3D (Boolean is3D){
			this.is3D = is3D;
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
