package com.capgemini.types;

import java.util.List;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class StudioTO {
	
	@NonNull
	private Long id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String country;
	
	@NonNull
	private List<Long> movies;
	
	@NonNull
	private List<Long> actors;

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

	public List<Long> getActors() {
		return actors;
	}

	public void setActors(List<Long> actors) {
		this.actors = actors;
	}

}
