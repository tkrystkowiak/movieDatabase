package com.capgemini.types;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class MovieTO {
	
	@NonNull
	private Long id;
	
	@NonNull
	private String title;
	
	@NonNull
	private String genre;
	
	@NonNull
	private String type;
	
	@NonNull
	private Integer length;
	
	@NonNull
	private LocalDate dateOfPremiere;
	
	@NonNull
	private String country;
	
	@NonNull
	private Boolean is3D;
	
	@NonNull
	private Integer budget;
	
	@NonNull
	private Integer totalRevenue;
	
	@NonNull
	private Integer firstWeekRevenue;
	
	private List<Long> cast;
	
	private Long studio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public LocalDate getDateOfPremiere() {
		return dateOfPremiere;
	}

	public void setDateOfPremiere(LocalDate dateOfPremiere) {
		this.dateOfPremiere = dateOfPremiere;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getIs3D() {
		return is3D;
	}

	public void setIs3D(Boolean is3d) {
		is3D = is3d;
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	public Integer getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Integer totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Integer getFirstWeekRevenue() {
		return firstWeekRevenue;
	}

	public void setFirstWeekRevenue(Integer firstWeekRevenue) {
		this.firstWeekRevenue = firstWeekRevenue;
	}

	public List<Long> getCast() {
		return cast;
	}

	public void setCast(List<Long> cast) {
		this.cast = cast;
	}

	public Long getStudio() {
		return studio;
	}

	public void setStudio(Long studio) {
		this.studio = studio;
	}
	
	
	
}
