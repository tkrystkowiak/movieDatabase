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

import com.capgemini.domain.CooperationEntity;
import com.capgemini.domain.MovieEntity;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class ActorTO {
	
	private Long version;
	
	private Long id;
	
	@NonNull
	private String firstName;
	
	@NonNull
	private String lastName;
	
	@NonNull
	private LocalDate birthDate;
	
	@NonNull
	private String country;
	
	private List<Long> cooperations;
	
	private List<Long> movies;

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
	
}
