package com.capgemini.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.capgemini.domain.ActorEntity.Builder;

import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "STUDIOS")
@EntityListeners(EntityListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StudioEntity extends AbstractEntity {

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String country;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "studio")
	private List<MovieEntity> movies;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CooperationEntity> cooperations;
	
	public StudioEntity() {
	}
	
	public StudioEntity(Builder builder) {
		super(builder.version,builder.id);
		this.name = builder.name;
		this.country = builder.country;
		this.movies = builder.movies;
		this.cooperations = builder.cooperations;
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

	public List<MovieEntity> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieEntity> movies) {
		this.movies = movies;
	}
	
	public List<CooperationEntity> getCooperation() {
		return cooperations;
	}

	public void setCooperation(List<CooperationEntity> cooperations) {
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
		private List<MovieEntity> movies;
		private List<CooperationEntity> cooperations;
		
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
		
		public Builder withMovies (List<MovieEntity> movies){
			this.movies = movies;
			return this;
		}
		
		public Builder withCooperations (List<CooperationEntity> cooperations){
			this.cooperations = cooperations;
			return this;
		}
		
		public StudioEntity build(){
			return new StudioEntity(this);
		}
		
	}
	
}
