package com.capgemini.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.domain.MovieEntity;

public interface MovieDao extends JpaRepository<MovieEntity,Long>, CustomMovieDao{
	
	List<MovieEntity> findByCountry(String country);
	
	List<MovieEntity> findByTitle(String title);
	
	@Query("select m from MovieEntity m where m.title = :title and m.country = :country"
			+ " and m.dateOfPremiere between :startDate and :endDate")
	List<MovieEntity> findByTitleCountryAndDate(@Param("title") String title,@Param("country") String country,
			@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
	
}
