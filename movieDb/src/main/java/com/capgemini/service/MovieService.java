package com.capgemini.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.types.MovieTO;
import com.capgemini.types.SearchCriteria;

public interface MovieService {
	
	void addMovie(MovieTO movie) throws InvalidDataException, ParseException;
	
	List<MovieTO> findMovieByCriteria(SearchCriteria criteria);
	
	Double calculateAverageFirstWeekRevenue();
	
	Double calculateAverageTotalRevenue();
	
	Integer calculateCombinedRevenueOfTopExpepensiveMovies(int NumberOfMovies);
	
	Integer calculateCombinedMoviesBudgetInGivenPeriod(LocalDate startDate,LocalDate endDate);
	
	List<MovieTO> findLongestMoviesByGivenStudioInGivenPeriod(long studioId, LocalDate startDate, LocalDate endDate);
}
