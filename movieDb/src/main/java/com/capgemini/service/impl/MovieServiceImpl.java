package com.capgemini.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.dao.MovieDao;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.mappers.MovieMapper;
import com.capgemini.service.MovieService;
import com.capgemini.types.MovieTO;

public class MovieServiceImpl implements MovieService {

	private final MovieDao movieDao;
	private final MovieMapper movieMapper;

	@Autowired
	public MovieServiceImpl(MovieDao movieDao, MovieMapper movieMapper) {
		this.movieDao = movieDao;
		this.movieMapper = movieMapper;
	}

	@Override
	public void addMovie(MovieTO movie) throws InvalidDataException, ParseException {
		if(movie.getFirstWeekRevenue()>movie.getTotalRevenue()){
			throw new InvalidDataException("First week revenue cannot be bigger than total");
		}
		
		LocalDate premiere = movie.getDateOfPremiere();
		int year = premiere.getYear();
		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year, 12, 31);
		
		if(!movieDao.findByTitleCountryAndDate(movie.getTitle(), movie.getCountry(), startDate, endDate).isEmpty()){
			throw new InvalidDataException("There can be only one movie with identical title, country and date of premiere");
		}
		
		movieDao.save(movieMapper.mapOnEntity(movie));
	}

}
