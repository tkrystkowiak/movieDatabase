package com.capgemini.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.SearchCriteria;
import com.capgemini.types.StudioWithNumberOfMoviesTO;
import com.querydsl.core.Tuple;

public interface CustomMovieDao {
	
	List<MovieEntity> findByCriteria(SearchCriteria searchCriteria);
	
	List<MovieEntity> findWithQueryDSL(String title);
	
	List<MovieEntity> findByActorAndPeriodAndDifferentStudio(Long actorId,Long studioId, LocalDate startDate, LocalDate endDate);
	
	Double findAverageFirstWeekRevenue();

	Double findAverageTotalRevenue();

	Integer findCombinedRevenueOfTopExpensiveMovies(int numberOfMovies);

	Integer findCobinedBudgetOfFilmsInGivenPeriod(LocalDate startDate, LocalDate endDate);

	List<MovieEntity> findLongestMovieWithGivenStudioAndPeriod(Long studioId, LocalDate startDate, LocalDate endDate);

	List<StudioWithNumberOfMoviesTO> findNumerOfEachStudioMoviesInGivenPeriod(LocalDate startDate, LocalDate endDate);

	List<MovieEntity> findTopExpensiveMovies(int numberOfMovies);

	List<MovieEntity> findMoviesByPeriodWithMatchingIds(List<Long> movieIds, LocalDate startDate, LocalDate endDate);
}
