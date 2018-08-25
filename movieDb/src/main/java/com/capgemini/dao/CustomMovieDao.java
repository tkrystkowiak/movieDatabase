package com.capgemini.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.SearchCriteria;

public interface CustomMovieDao {
	
	List<MovieEntity> findByCriteria(SearchCriteria searchCriteria);
	
	List<MovieEntity> findWithQueryDSL(String title);
	
	List<MovieEntity> findByActorAndPeriod(Long actorId, LocalDate startDate, LocalDate endDate);
	
	List<MovieEntity> findByActorAndPeriodAndDifferentStudio(Long actorId,Long studioId, LocalDate startDate, LocalDate endDate);
}
