package com.capgemini.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACTORS")
@EntityListeners(EntityListener.class)
public class ActorEntity extends AbstractEntity {	
	
	@Column(name="first_name", nullable = false, length = 50)
	private String firstName;
	
	@Column(name="last_name", nullable = false, length = 50)
	private String lastName;
	
	@Column(name="birth_date",nullable = false)
	private LocalDate birthDate;
	
	@Column(nullable = false)
	private String country;
	
	@OneToMany( mappedBy="actor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CooperationEntity> cooperations;
	
	@ManyToMany(cascade = CascadeType.MERGE, mappedBy = "cast")
	private List<MovieEntity> movies;
	
	public ActorEntity() {
	}
	
	public ActorEntity(Builder builder) {
		super(builder.version,builder.id);
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.birthDate = builder.birthDate;
		this.country = builder.country;
		this.cooperations = builder.cooperations;
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	public List<CooperationEntity> getCooperations() {
		return cooperations;
	}

	public void setCooperations(List<CooperationEntity> cooperations) {
		this.cooperations = cooperations;
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
		private Long version;
		private String firstName;
		private String lastName;
		private LocalDate birthDate;
		private String country;
		private List<CooperationEntity> cooperations;
		private List<MovieEntity> movies;
		
		public Builder withId (Long id){
			this.id = id;
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
		
		public Builder withBirthDate (LocalDate birthDate){
			this.birthDate = birthDate;
			return this;
		}
		
		public Builder withCountry (String country){
			this.country = country;
			return this;
		}
		
		public Builder withCooperations (List<CooperationEntity> cooperations){
			this.cooperations = cooperations;
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
