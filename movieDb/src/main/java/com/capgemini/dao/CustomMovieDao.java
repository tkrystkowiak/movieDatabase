package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.SearchCriteria;

public interface CustomMovieDao {
	
	List<MovieEntity> findByCriteria(SearchCriteria searchCriteria);
	
}
