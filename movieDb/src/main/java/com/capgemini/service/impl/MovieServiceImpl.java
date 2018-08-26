package com.capgemini.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.dao.MovieDao;
import com.capgemini.domain.SearchCriteria;
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

	@Override
	public List<MovieTO> findMovieByCriteria(SearchCriteria criteria) {
		return movieMapper.mapOnTOs(movieDao.findByCriteria(criteria));
	}

	@Override
	public Integer calculateAverageFirstWeekRevenue() {
		return movieDao.findAverageFirstWeekRevenue();
	}

	@Override
	public Integer calculateAverageTotalRevenue() {
		return movieDao.findAverageTotalRevenue();
	}

	@Override
	public Long calculateCombinedRevenueOfTopExpepensiveMovies(int numberOfMovies) {
		return movieDao.findCombinedRevenueOfTopExpensiveMovies(numberOfMovies);
	}

	@Override
	public Long calculateCombinedMoviesBudgetInGivenPeriod(LocalDate startDate, LocalDate endDate) {
		return movieDao.findCobinedBudgetOfFilmsInGivenPeriod(startDate, endDate);
	}

	@Override
	public List<MovieTO> findLongestMoviesByGivenStudioInGivenPeriod(long studioId, LocalDate startDate,
			LocalDate endDate) {
		return movieMapper.mapOnTOs(movieDao.findLongestMovieWithGivenStudioAndPeriod(studioId, startDate, endDate));
	}

}
