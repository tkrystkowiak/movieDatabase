package com.capgemini.dao.impl;

import java.util.List;

import com.capgemini.dao.CustomMovieDao;
import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.SearchCriteria;

public class CustomMovieDaoImpl implements CustomMovieDao {

	@Override
	public List<MovieEntity> findByCriteria(SearchCriteria searchCriteria) {
		
		
		return null;
	}
	
	public List<MovieEntity> findByTitle(String title){
		//QMovieEntity movie= QMovie.person;
		return null;
	}

}
