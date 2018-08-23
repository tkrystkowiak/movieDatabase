package com.capgemini.types;

import java.sql.Date;
import java.util.List;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class ActorTO {
	
	@NonNull
	private Long id;
	
	@NonNull
	private String firstName;
	
	@NonNull
	private String lastName;
	
	@NonNull
	private Date birthDate;
	
	@NonNull
	private String country;
	
	@NonNull
	private Long studio;
	
	@NonNull
	private List<Long> movies;

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

	public Long getStudio() {
		return studio;
	}

	public void setStudio(Long studio) {
		this.studio = studio;
	}

	public List<Long> getMovies() {
		return movies;
	}

	public void setMovies(List<Long> movies) {
		this.movies = movies;
	}
	
}
