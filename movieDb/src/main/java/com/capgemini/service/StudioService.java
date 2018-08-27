package com.capgemini.service;

import java.util.List;

import com.capgemini.types.StudioTO;
import com.capgemini.types.StudioWithNumberOfMoviesTO;
import com.querydsl.core.Tuple;

public interface StudioService {
	
	void addStudio(StudioTO studio);
	
	List<StudioWithNumberOfMoviesTO> calculeteNumberOfStudiosMoviesInGivenYear(int year);
	
}
