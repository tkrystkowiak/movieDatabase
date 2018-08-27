package com.capgemini.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.types.MovieTO;
import com.capgemini.types.SearchCriteria;

/**
 * This service handles requests concerning movies
 * 
 * @author tkrystko
 *
 */
public interface MovieService {
	
	/**
	 * Adds movie to database
	 * 
	 * @param movie
	 * @throws InvalidDataException
	 * @throws ParseException
	 */
	void addMovie(MovieTO movie) throws InvalidDataException, ParseException;
	
	/**
	 * Updates movie in database
	 * 
	 * @param movie
	 * @throws InvalidDataException
	 */
	void updateMovie(MovieTO movie) throws InvalidDataException;
	
	/**
	 * Deletes movie from database
	 * 
	 * @param movie
	 */
	void deleteMovie(Long movie);
	
	/**
	 * @param criteria
	 * @return List of movies matching given criteria
	 */
	List<MovieTO> findMovieByCriteria(SearchCriteria criteria);
	
	/**
	 * @return average revenue of movie in first week after premiere
	 */
	Double calculateAverageFirstWeekRevenue();
	
	/**
	 * @return average total revenue of movies
	 */
	Double calculateAverageTotalRevenue();
	
	/**
	 * @param NumberOfMovies
	 * @return Cobmined revenue of given number most expensive movies
	 */
	Integer calculateCombinedRevenueOfTopExpepensiveMovies(int NumberOfMovies);
	
	/**
	 * @param startDate
	 * @param endDate
	 * @return Combined budget of movies with premiers in given period of time
	 */
	Integer calculateCombinedMoviesBudgetInGivenPeriod(LocalDate startDate,LocalDate endDate);
	
	/**
	 * @param studioId
	 * @param startDate
	 * @param endDate
	 * @return List of longest movies produced by given studio in given period of time
	 */
	List<MovieTO> findLongestMoviesByGivenStudioInGivenPeriod(long studioId, LocalDate startDate, LocalDate endDate);


}
