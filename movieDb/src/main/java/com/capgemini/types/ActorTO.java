package com.capgemini.types;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.CooperationEntity;
import com.capgemini.domain.MovieEntity;

import lombok.Builder;
import lombok.NonNull;

public class ActorTO {
	
	private Long version;
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate birthDate;
	
	private String country;
	
	private List<Long> cooperations;
	
	private List<Long> movies;
	
	public ActorTO(Builder builder) {
		this.version = builder.version;
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.birthDate = builder.birthDate;
		this.country = builder.country;
		this.cooperations = builder.cooperations;
		this.movies = builder.movies;
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

	public List<Long> getCooperations() {
		return cooperations;
	}

	public void setCooperations(List<Long> cooperations) {
		this.cooperations = cooperations;
	}

	public List<Long> getMovies() {
		return movies;
	}

	public void setMovies(List<Long> movies) {
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
		private List<Long> cooperations;
		private List<Long> movies;
		
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
		
		public Builder withCooperations (List<Long> cooperations){
			this.cooperations = cooperations;
			return this;
		}
		
		public Builder withMovies (List<Long> movies){
			this.movies = movies;
			return this;
		}
		
		public ActorTO build(){
			return new ActorTO(this);
		}
		
	}
	
}
