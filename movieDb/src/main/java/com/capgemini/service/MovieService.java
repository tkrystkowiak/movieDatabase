package com.capgemini.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import com.capgemini.domain.SearchCriteria;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.types.MovieTO;

public interface MovieService {
	
	void addMovie(MovieTO movie) throws InvalidDataException, ParseException;
	
	List<MovieTO> findMovieByCriteria(SearchCriteria criteria);
	
	Integer calculateAverageFirstWeekRevenue();
	
	Integer calculateAverageTotalRevenue();
	
	Long calculateCombinedRevenueOfTopExpepensiveMovies(int NumberOfMovies);
	
	Long calculateCombinedMoviesBudgetInGivenPeriod(LocalDate startDate,LocalDate endDate);
	
	List<MovieTO> findLongestMoviesByGivenStudioInGivenPeriod(long studioId, LocalDate startDate, LocalDate endDate);
}
