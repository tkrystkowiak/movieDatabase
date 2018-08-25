package com.capgemini.types;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.capgemini.domain.CooperationEntity;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class StudioTO {
	
	private Long id;
	
	private Long version;
	
	@NonNull
	private String name;
	
	@NonNull
	private String country;
	
	private List<Long> movies;
	
	private List<Long> cooperations;

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

	

}
