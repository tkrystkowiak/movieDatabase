package com.capgemini.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.capgemini.domain.ActorEntity.Builder;

import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "STUDIOS")
@EntityListeners(EntityListener.class)
@SuperBuilder
public class StudioEntity extends AbstractEntity {
	
	@NonNull
	@Column(nullable = false)
	private String name;
	
	@NonNull
	@Column(nullable = false)
	private String country;
	
	@NonNull
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "studio")
	private List<MovieEntity> movies;
	
	@NonNull
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "studio")
	private List<ActorEntity> actors;
	
	public StudioEntity() {
	}
	
	public StudioEntity(Builder builder) {
		super(builder.version, builder.id, builder.persistTime, builder.updateTime);
		this.name = builder.name;
		this.country = builder.country;
		this.movies = builder.movies;
		this.actors = builder.actors;
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

	public List<ActorEntity> getActors() {
		return actors;
	}

	public void setActors(List<ActorEntity> actors) {
		this.actors = actors;
	}
	
	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static class Builder{
		
		private Long id;
		private Time persistTime;
		private Time updateTime;
		private Long version;
		private String name;
		private String country;
		private List<MovieEntity> movies;
		private List<ActorEntity> actors;
		
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
		
		public Builder withActors (List<ActorEntity> actors){
			this.actors = actors;
			return this;
		}
		
		public StudioEntity build(){
			return new StudioEntity(this);
		}
		
	}
	
}
