package com.capgemini.service;

import java.util.List;

import com.capgemini.types.StudioTO;
import com.capgemini.types.StudioWithNumberOfMoviesTO;
import com.querydsl.core.Tuple;

/**
 * This service handles requests concerning studios
 * 
 * @author tkrystko
 *
 */
public interface StudioService {
	
	/**
	 * Adds new studio to database
	 * 
	 * @param studio
	 */
	void addStudio(StudioTO studio);
	
	/**
	 * Updates studio in database
	 * 
	 * @param studio
	 */
	void updateStudio(StudioTO studio);
	
	/**
	 * Deletes studio from database
	 * 
	 * @param studio
	 */
	void deleteStudio(Long studio);
	
	/**
	 * @param year
	 * @return Calculates number of movies per studios in given year
	 */
	List<StudioWithNumberOfMoviesTO> calculeteNumberOfStudiosMoviesInGivenYear(int year);
	
}
