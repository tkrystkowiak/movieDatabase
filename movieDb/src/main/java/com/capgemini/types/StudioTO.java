package com.capgemini.types;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.capgemini.domain.CooperationEntity;

import lombok.Builder;
import lombok.NonNull;

public class StudioTO {
	
	private Long id;
	
	private Long version;
	
	private String name;
	
	private String country;
	
	private List<Long> movies;
	
	private List<Long> cooperations;
	
	public StudioTO(Builder builder) {
		this.id = builder.id;
		this.version = builder.version;
		this.name = builder.name;
		this.country = builder.country;
		this.movies = builder.movies;
		this.cooperations = builder.cooperations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Long> getMovies() {
		return movies;
	}

	public void setMovies(List<Long> movies) {
		this.movies = movies;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<Long> getCooperations() {
		return cooperations;
	}

	public void setCooperations(List<Long> cooperations) {
		this.cooperations = cooperations;
	}
	
	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static class Builder{
		
		private Long id;
		
		private Long version;
		
		private String name;
	
		private String country;
		
		private List<Long> movies;
		
		private List<Long> cooperations;
		
		public Builder withId (Long id){
			this.id = id;
			return this;
		}
		
		public Builder withVersion (Long version){
			this.version = version;
			return this;
		}
		
		public Builder withName (String name){
			this.name = name;
			return this;
		}
		
		public Builder withCountry (String country){
			this.country = country;
			return this;
		}
		
		public Builder withMovies (List<Long> movies){
			this.movies = movies;
			return this;
		}
		
		public Builder withCooperations (List<Long> cooperations){
			this.cooperations = cooperations;
			return this;
		}
		
		public StudioTO build(){
			return new StudioTO(this);
		}

	}
	
}
