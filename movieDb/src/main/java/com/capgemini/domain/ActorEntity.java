package com.capgemini.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.NonNull;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "ACTORS")
@EntityListeners(EntityListener.class)
@SuperBuilder
public class ActorEntity extends AbstractEntity {	
	
	@NonNull
	@Column(name="first_name", nullable = false, length = 50)
	private String firstName;
	
	@NonNull
	@Column(name="last_name", nullable = false, length = 50)
	private String lastName;
	
	@NonNull
	@Column(name="birth_date",nullable = false)
	private Date birthDate;
	
	@NonNull
	@Column(nullable = false)
	private String country;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private StudioEntity studio;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "cast")
	private List<MovieEntity> movies;
	
	public ActorEntity() {
	}
	
	public ActorEntity(Builder builder) {
		super(builder.version,builder.id, builder.persistTime, builder.updateTime);
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.birthDate = builder.birthDate;
		this.country = builder.country;
		this.studio = builder.studio;
		this.movies = builder.movies;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public StudioEntity getStudio() {
		return studio;
	}

	public void setStudio(StudioEntity studio) {
		this.studio = studio;
	}

	public List<MovieEntity> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieEntity> movies) {
		this.movies = movies;
	}
	
	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static class Builder{
		
		private Long id;
		private Time persistTime;
		private Time updateTime;
		private Long version;
		private String firstName;
		private String lastName;
		private Date birthDate;
		private String country;
		private StudioEntity studio;
		private List<MovieEntity> movies;
		
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
		
		public Builder withFirstName (String firstName){
			this.firstName = firstName;
			return this;
		}
		
		public Builder withLastName (String lastName){
			this.lastName = lastName;
			return this;
		}
		
		public Builder withBirthDate (Date birthDate){
			this.birthDate = birthDate;
			return this;
		}
		
		public Builder withCountry (String country){
			this.country = country;
			return this;
		}
		
		public Builder withStudio (StudioEntity studio){
			this.studio = studio;
			return this;
		}
		
		public Builder withMovies (List<MovieEntity> movies){
			this.movies = movies;
			return this;
		}
		
		public ActorEntity build(){
			return new ActorEntity(this);
		}
		
	}
	
}
