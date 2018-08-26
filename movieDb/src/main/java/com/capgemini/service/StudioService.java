package com.capgemini.service;

import java.util.List;

import javax.persistence.Tuple;

import com.capgemini.types.StudioTO;

public interface StudioService {
	
	void addStudio(StudioTO studio);
	
	List<Tuple> calculeteNumberOfStudiosMoviesInGivenYear(int year);
	
}
