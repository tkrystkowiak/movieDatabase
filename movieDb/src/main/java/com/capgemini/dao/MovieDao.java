package com.capgemini.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.MovieEntity;

public interface MovieDao extends CrudRepository<MovieEntity,Long>, CustomMovieDao{
	
	List<MovieEntity> findByCountry(String country);
	
	List<MovieEntity> findByTitle(String title);
	
}
